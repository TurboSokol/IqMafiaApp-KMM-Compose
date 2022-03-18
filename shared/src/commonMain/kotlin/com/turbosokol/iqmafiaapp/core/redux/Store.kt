package com.turbosokol.iqmafiaapp.core.redux

import com.turbosokol.iqmafiaapp.common.app.AppState
import com.turbosokol.iqmafiaapp.common.app.RootReducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface GeneralState
interface Action
interface Effect
object None: Action
object Empty: Effect

interface Store<S : GeneralState, A : Action, E : Effect> {
    fun observeAsState(): StateFlow<S>
    fun dispatch(action: A)
    fun getState(): S
    fun observeSideEffect(): Flow<E>
}

open class ReduxStore(
    private val reducer: RootReducer,
    appState: AppState,
    private val middlewares: List<Middleware<AppState>>
): Store<AppState, Action, Effect> {

    private val observableState = MutableStateFlow(appState)
    private val observableSideEffect = MutableSharedFlow<Effect>()

    override fun observeAsState(): StateFlow<AppState> = observableState

    override fun dispatch(action: Action) {
        val oldState = observableState.value
        val newState = reducer.reduce(oldState, action)

        middlewares.forEach { middleware ->
            val scope = CoroutineScope(Dispatchers.Main + Job())
            scope.launch {
                    middleware.process(newState, action, observableSideEffect).collect {
                        dispatch(it)
                    }
                }
            }
        }


    override fun getState(): AppState {
        return observableState.value
    }

    override fun observeSideEffect(): Flow<Effect> = observableSideEffect
}