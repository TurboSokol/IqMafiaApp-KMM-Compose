package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeCardsScreenMiddleware : Middleware<JudgeCardsScreenState> {
    override suspend fun execute(
        state: JudgeCardsScreenState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
        TODO("Not yet implemented")
    }
}