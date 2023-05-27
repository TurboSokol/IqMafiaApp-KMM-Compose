package com.turbosokol.iqmafiaapp.features.judge.screens.score

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeScoreScreenReducer : Reducer<JudgeScoreScreenState> {
    override fun reduce(oldState: JudgeScoreScreenState, action: Action): JudgeScoreScreenState {
        return when (action) {
            is JudgeScoreScreenAction.Init -> {
                JudgeScoreScreenState.getInitState()
            }

            is JudgeScoreScreenAction.EditTotalScore -> {
                oldState.copy(playersTotalScore = action.playersTotalScore)
            }

            is JudgeScoreScreenAction.EditBonusScore -> {
                oldState.copy(playerBonusScore = action.playerBonusScore)
            }

            else -> oldState
        }
    }
}