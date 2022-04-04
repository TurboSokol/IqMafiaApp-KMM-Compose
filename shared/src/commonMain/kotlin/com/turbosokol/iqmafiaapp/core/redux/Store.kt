package com.turbosokol.iqmafiaapp.core.redux

import com.turbosokol.iqmafiaapp.common.app.AppState
import com.turbosokol.iqmafiaapp.common.app.RootReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

interface GeneralState
interface Action
interface Effect
object None : Action
object Empty : Effect

interface Store<S : GeneralState, A : Action, E : Effect> {
    fun observeAsState(): StateFlow<S>
    fun execute(action: A)
    fun getState(): S
    fun observeSideEffect(): Flow<E>
}

@ExperimentalTime
open class ReduxStore(
    private val reducer: RootReducer,
    defaultState: AppState,
    private val middlewares: List<Middleware<AppState>>
) : Store<AppState, Action, Effect>, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val observableState = MutableStateFlow(defaultState)
    private val observableSideEffect = MutableSharedFlow<Effect>()

    override fun observeAsState(): StateFlow<AppState> = observableState

    override fun execute(action: Action) {
        val oldState = observableState.value
        val newState = reducer.reduce(oldState, action)

        middlewares.forEach { middleware ->
            launch {
                middleware.process(newState, action, observableSideEffect).collect {
                    execute(it)
                }
            }
        }
    }


    override fun getState(): AppState {
        return observableState.value
    }

    override fun observeSideEffect(): Flow<Effect> = observableSideEffect
}