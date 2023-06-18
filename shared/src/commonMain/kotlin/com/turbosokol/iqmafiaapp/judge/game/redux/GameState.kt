package com.turbosokol.iqmafiaapp.judge.game.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

data class GameState(
    val gameId: Int,
    val gameStarted: Boolean,
    val gameEnded: Boolean,
    val teamWon: String,
    val firstKill: Int
): GeneralState {
    companion object {
        fun getDefaultState(): GameState {
            return GameState(
                gameId = 0, //TODO:: wait for firebase db
                gameStarted = false,
                gameEnded = false,
                teamWon = "",
                firstKill = 0
            )
        }
    }
}

sealed class GameAction: Action {
    object StartGame: GameAction()
    object EndGame: GameAction()
    data class AddFirstKill(val firstKill: Int): GameAction()
}

object GameEndedEffect: Effect