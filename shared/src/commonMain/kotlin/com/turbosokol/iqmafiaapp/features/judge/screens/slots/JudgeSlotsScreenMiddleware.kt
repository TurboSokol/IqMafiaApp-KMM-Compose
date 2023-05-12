package com.turbosokol.iqmafiaapp.features.judge.screens.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeSlotsScreenMiddleware : Middleware<JudgeSlotsScreenState> {
    override suspend fun execute(
        state: JudgeSlotsScreenState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
       return emptyFlow()
    }
}


