package com.turbosokol.iqmafiaapp.features.judge.screens.day

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class DayScreenState(
//    MARK:: fetch actual state in VIEW LAYER
//    val playersState: PlayersState,

//    MARK:: fetch actual state in VIEW LAYER
//    val roundState: RoundState,

    val playersFaults: List<Int>,
    val voteResults: Int

) : GeneralState {
    companion object {
        // isVotingActive = listOf(false)
        // isVotingActive.count = JudgeRoundState.voteCandidatesList.count
        // when EACH candidates Voting starts voted index become to true for 2 second and after 2 secs again become to false
        fun getInitState(): DayScreenState = DayScreenState(
            playersFaults = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            voteResults = -1
        )
    }
}

sealed class DayScreenAction: Action {
    object Init: DayScreenAction()

    data class UpdateFaults(val playersFaults: List<Int>): DayScreenAction()
}