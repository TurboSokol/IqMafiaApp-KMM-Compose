package com.turbosokol.iqmafiaapp.features.judge.screens.score

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class ScoreScreenReducer : Reducer<ScoreScreenState> {
    override fun reduce(oldState: ScoreScreenState, action: Action): ScoreScreenState {
        return when (action) {
            is ScoreScreenAction.Init -> {
                ScoreScreenState.getInitState()
            }

            is ScoreScreenAction.EditTotalScore -> {
                oldState.copy(playersTotalScore = action.playersTotalScore)
            }

            is ScoreScreenAction.EditBonusScore -> {
                oldState.copy(playerBonusScore = action.playerBonusScore)
            }

            else -> oldState
        }
    }
}