package com.turbosokol.iqmafiaapp.judge.game.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

data class GameState(
    val gameStarted: Boolean,
    val gameEnded: Boolean,
): GeneralState {
    companion object {
        fun getDefaultState(): GameState {
            return GameState(
                gameStarted = false,
                gameEnded = false
            )
        }
    }
}

sealed class GameAction: Action {
    data class StartGame(val gameStarted: Boolean): GameAction()
    data class EndGame(val gameEnded: Boolean): GameAction()
}

object GameEndedEffect: Effect