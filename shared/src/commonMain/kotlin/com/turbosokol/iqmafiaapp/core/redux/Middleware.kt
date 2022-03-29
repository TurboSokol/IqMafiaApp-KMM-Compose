package com.turbosokol.iqmafiaapp.core.redux

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface Middleware<T : GeneralState> {
    suspend fun process(state: T, action: Action, sideEffect: MutableSharedFlow<Effect>): Flow<Action>
}