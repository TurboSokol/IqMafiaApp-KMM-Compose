package com.turbosokol.iqmafiaapp.features.judge.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersAction

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeRoundReducer : Reducer<JudgeRoundState> {
    override fun reduce(oldState: JudgeRoundState, action: Action): JudgeRoundState {
        return when (action) {
            is JudgeRoundAction.Init -> {
                JudgeRoundState.getInitState()
            }

            is JudgeRoundAction.UpdateVoteOrder -> {
                oldState.copy(voteOrder = action.voteOrder)
            }

            else -> oldState
        }
    }
}