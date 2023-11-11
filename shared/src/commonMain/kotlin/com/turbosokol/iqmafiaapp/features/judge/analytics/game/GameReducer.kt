package com.turbosokol.iqmafiaapp.features.judge.analytics.game

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class GameReducer: Reducer<GameState> {
    override fun reduce(oldState: GameState, action: Action): GameState {
        return when(action) {
            is GameAction.EndOfRound -> {
                oldState.copy(playersVoted = oldState.playersVoted.plus(action.votedPlayer), roundCount = oldState.roundCount+1)
            }

            is GameAction.UpdateMainPoints -> {
                oldState.copy(mainPoints = action.mainPoints)
            }

            is GameAction.UpdateDopPoints -> {
                oldState.copy(dopPoints = action.dopPoints)
            }

            is GameAction.UpdateComments -> {
                oldState.copy(comments = action.comments)
            }

            else -> oldState
        }
    }
}