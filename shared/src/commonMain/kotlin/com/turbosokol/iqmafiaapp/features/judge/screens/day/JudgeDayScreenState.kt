package com.turbosokol.iqmafiaapp.features.judge.screens.day

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgeDayScreenState(
//    MARK:: fetch actual state in VIEW LAYER
//    val judgePlayersState: JudgePlayersState,

//    MARK:: fetch actual state in VIEW LAYER
//    val judgeRoundState: JudgeRoundState,

    val playersFaults: List<Int>,
    val isVotingActive: List<Boolean>,
    val voteResults: List<Int>

) : GeneralState {
    companion object {
        // isVotingActive = listOf(false)
        // isVotingActive.count = JudgeRoundState.voteCandidatesList.count
        // when EACH candidates Voting starts voted index become to true for 2 second and after 2 secs again become to false
        fun getInitState(): JudgeDayScreenState = JudgeDayScreenState(
            playersFaults = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            isVotingActive = listOf(),
            voteResults = listOf()
        )
    }
}

sealed class JudgeDayScreenAction: Action {
    object Init: JudgeDayScreenAction()

    data class UpdateFaults(val playersFaults: List<Int>): JudgeDayScreenAction()

    // isVotingActive.count = JudgeRoundState.voteCandidatesList.count
    // when EACH candidates Voting starts voted index become to true for 2 second and after 2 secs again become to false
    data class ProcessVoting(val isVotingActive: List<Boolean>): JudgeDayScreenAction()

    // MARK:: implement UI response with sorted list of voted players
    // GRAB JudgeRoundState to localDB (JudgeRoundAction.RoundCompleted)
    data class EndVote(val voteResults: List<Int>): JudgeDayScreenAction()
}