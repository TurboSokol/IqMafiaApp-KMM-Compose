package com.turbosokol.iqmafiaapp.features.judge.analytics.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class RoundMiddleware : Middleware<AppState> {
    override suspend fun execute(
        state: AppState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> = flow {
        run {
            when (action) {
                is RoundAction.RoundCompleted -> {
                    emit(
                        PlayersAction.UpdateVoteNominations(
                            listOf(
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false
                            )
                        )
                    )
                    emit(GameAction.EndOfRound(action.votedPlayer))
                }

                else -> return@flow
            }
        }
    }
}
