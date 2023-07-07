package com.turbosokol.iqmafiaapp.features.judge.round

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgeRoundState(
    val roundId: Int,
    val gameId: Int,
    val roundCount: Int,
    val voteOrder: List<Int>,
    val voteResult: Int
) : GeneralState {
    companion object {
        fun getInitState(): JudgeRoundState = JudgeRoundState(
            roundId = 0,
            gameId = 0,
            roundCount = 0,
            voteOrder = listOf(),
            voteResult = 0
        )
    }
}

sealed class JudgeRoundAction : Action {
    //Init clear
    object Init : JudgeRoundAction()

    data class UpdateVoteOrder(val voteOrder: List<Int>): JudgeRoundAction()
    //write in local DB
    object RoundCompleted : JudgeRoundAction()

}
