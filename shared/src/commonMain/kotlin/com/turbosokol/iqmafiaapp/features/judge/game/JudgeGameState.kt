package com.turbosokol.iqmafiaapp.features.judge.game

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgeGameState(
    val localGameId: Int,
    val gameId: Int,
    val judgeId: Int,
    val typeEnd: JudgeGameEndType?,
    val winnerTeam: JudgeWinnerTeamType?,
    val isGameIdRecieved: Boolean
) : GeneralState {
    companion object {
        fun getInitState(): JudgeGameState = JudgeGameState(
            localGameId = 0,
            gameId = 0,
            judgeId = 0,
            typeEnd = null,
            winnerTeam = null,
            isGameIdRecieved = false
        )
    }
}

sealed class JudgeGameAction : Action {
    //clear state to InitState
    object Init: JudgeGameAction()

    object GetGameId: JudgeGameAction()

    //read gameId from WEB DB or local db
    data class StartGame(val gameId: Int, val judgeId: Int): JudgeGameAction()

    //make REST GET to recap gameID
    data class EndGame(val gameId: Int, val typeEnd: JudgeGameEndType?, val winnerTeam: JudgeWinnerTeamType?): JudgeGameAction()
}

enum class JudgeGameEndType {
    DEFAULT, CLEAR, PPK
}

enum class JudgeWinnerTeamType {
    RED, BLACk, DRAW
}