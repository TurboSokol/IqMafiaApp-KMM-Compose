package com.turbosokol.iqmafiaapp.core.redux

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.time.ExperimentalTime

interface Middleware<T : GeneralState> {
    @ExperimentalTime
    suspend fun process(state: T, action: Action, sideEffect: MutableSharedFlow<Effect>): Flow<Action>
}