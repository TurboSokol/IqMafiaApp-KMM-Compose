package com.turbosokol.iqmafiaapp.judge.game.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class GameMiddleware: Middleware<GameState> {
    override suspend fun process(
        state: GameState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
        TODO("Not yet implemented")
    }
}