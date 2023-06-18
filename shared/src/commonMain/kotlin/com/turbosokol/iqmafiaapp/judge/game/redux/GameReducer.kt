package com.turbosokol.iqmafiaapp.judge.game.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

class GameReducer: Reducer<GameState> {
    override fun reduce(oldState: GameState, action: Action): GameState {
        return when (action) {
            is GameAction.StartGame -> {
                oldState.copy(gameStarted = true, gameEnded = false)
            }

            is GameAction.EndGame -> {
                oldState.copy(gameEnded = true, gameStarted = false)
            }

            is GameAction.AddFirstKill -> {
                oldState.copy(firstKill = action.firstKill)
            }

            else -> oldState
        }
    }
}