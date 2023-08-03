package com.turbosokol.iqmafiaapp.features.judge.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeRoundMiddleware : Middleware<AppState> {
    override suspend fun execute(
        state: AppState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> = flow {
        run {
            when (action) {
                is JudgeRoundAction.RoundCompleted -> {
                    emit(
                        JudgePlayersAction.UpdateVoteNominations(
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
                }

                else -> return@flow
            }
        }
    }
}
