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
    val voteCandidates: List<Int>,
    val voteResult: Int
) : GeneralState {
    companion object {
        fun getInitState(): JudgeRoundState = JudgeRoundState(
            roundId = 0,
            gameId = 0,
            roundCount = 0,
            voteCandidates = listOf<Int>(),
            voteResult = 0
        )
    }
}

sealed class JudgeRoundAction : Action {
    //Init clear
    object Init : JudgeRoundAction()

    //write in local DB
    object RoundCompleted : JudgeRoundAction()

}