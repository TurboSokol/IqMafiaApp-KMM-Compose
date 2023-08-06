package com.turbosokol.iqmafiaapp.features.judge.analytics.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class RoundState(
    val roundId: Int,
    val gameId: Int,
    val roundCount: Int,
    val voteCandidates: List<Int>,
    val voteResult: Map<Int, Int>
) : GeneralState {
    companion object {
        fun getInitState(): RoundState = RoundState(
            roundId = 0,
            gameId = 0,
            roundCount = 0,
            voteCandidates = listOf(),
            voteResult = emptyMap<Int, Int>()
        )
    }
}

sealed class RoundAction : Action {
    //Init clear
    object Init : RoundAction()

    data class UpdateVoteOrder(val voteOrder: List<Int>): RoundAction()

    data class UpdateVoteResults(val voteCandidates: Map<Int, Int>): RoundAction()

    //write in local DB
    data class RoundCompleted(val votedPlayer: Int) : RoundAction()

}
