package com.turbosokol.iqmafiaapp.features.judge.screens.score

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgeScoreScreenState(
    //list of 10 players,
    //each player have final summ of points
    val playersTotalScore: List<Double>,
    //list of 10 players,
    //each player have bonus (achievements) point w\o final summ
    val playerBonusScore: List<Double>
): GeneralState {
    companion object {
        fun getInitState(): JudgeScoreScreenState = JudgeScoreScreenState(
            //list index == player slot
            playersTotalScore = listOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
            playerBonusScore = listOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        )
    }
}

sealed class JudgeScoreScreenAction: Action {
    object Init: JudgeScoreScreenAction()
    //set some fields editable
    data class EditTotalScore(val playersTotalScore: List<Double>): JudgeScoreScreenAction()
    data class EditBonusScore(val playerBonusScore: List<Double>): JudgeScoreScreenAction()

}
