package com.turbosokol.iqmafiaapp.judge.game.redux

import com.turbosokol.iqmafiaapp.common.app.AppState
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GameMiddleware(): Middleware<AppState> {
    override suspend fun process(
        state: AppState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
        return emptyFlow()
    }
}