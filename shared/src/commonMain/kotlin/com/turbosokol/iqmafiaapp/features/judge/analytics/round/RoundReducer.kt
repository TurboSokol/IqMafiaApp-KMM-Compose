package com.turbosokol.iqmafiaapp.features.judge.analytics.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class RoundReducer : Reducer<RoundState> {
    override fun reduce(oldState: RoundState, action: Action): RoundState {
        return when (action) {
            is RoundAction.Init -> {
                RoundState.getInitState()
            }

            is RoundAction.UpdateVoteOrder -> {
                oldState.copy(voteCandidates = action.voteOrder)
            }

            is RoundAction.UpdateVoteResults -> {
                oldState.copy(voteResult = action.voteCandidates)
            }

            is RoundAction.RoundCompleted -> {
                oldState.copy(roundCount = oldState.roundCount+1, voteCandidates = emptyList(), voteResult = emptyMap())
            }

            else -> oldState
        }
    }
}