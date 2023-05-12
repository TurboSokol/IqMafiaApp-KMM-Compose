package com.turbosokol.iqmafiaapp.features.judge.screens.day

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeDayScreenReducer : Reducer<JudgeDayScreenState> {
    override fun reduce(oldState: JudgeDayScreenState, action: Action): JudgeDayScreenState {
        return when (action) {
            is JudgeDayScreenAction.Init -> {
                JudgeDayScreenState.getInitState()
            }

            //add or remove player faults
            //player slot = playerFaults.index
            //faults count = int in index
            is JudgeDayScreenAction.UpdateFaults -> {
                oldState.copy(playersFaults = action.playersFaults)
            }

            // init isVotingActive = listOf(false)
            // isVotingActive.count = JudgeRoundState.voteCandidatesList.count
            // when EACH candidates Voting starts voted index become to true for 2 second and after 2 secs again become to false
            is JudgeDayScreenAction.ProcessVoting -> {
                oldState.copy(isVotingActive = action.isVotingActive)
            }

            //list of voted players
            is JudgeDayScreenAction.EndVote -> {
                oldState.copy(isVotingActive = emptyList<Boolean>(), voteResults = action.voteResults)
            }

            else -> oldState
        }
    }
}