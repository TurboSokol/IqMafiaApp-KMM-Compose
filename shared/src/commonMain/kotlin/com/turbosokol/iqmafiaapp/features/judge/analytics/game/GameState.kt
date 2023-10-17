package com.turbosokol.iqmafiaapp.features.judge.analytics.game

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class GameState(
    val localGameId: Int,
    val gameId: Int,
    val judgeId: Int,
    val typeEnd: GameEndType?,
    val winnerTeam: WinnerTeamType?,
    val isGameIdReceived: Boolean,
    //position or profileId
    val playersKilled: List<Int>,
    //position or profileId
    val playersVoted: List<Int>,
    //map to game_profile table
    val bestMove: List<Int>,
    //count of rounds in the game
    val roundCount: Int,
    //summary points
    val summaryPoints: List<Double>,
    val dopPoints: List<Double>,
    //only for team win points
    val mainPoints: List<Double>
) : GeneralState {
    companion object {
        fun getInitState(): GameState = GameState(
            localGameId = 0,
            gameId = 0,
            judgeId = 0,
            typeEnd = null,
            winnerTeam = null,
            isGameIdReceived = false,
            playersKilled = emptyList(),
            playersVoted = emptyList(),
            bestMove = emptyList(),
            roundCount = 0,
            summaryPoints = emptyList(),
            dopPoints = emptyList(),
            mainPoints = emptyList()
        )
    }
}

sealed class GameAction : Action {
    //clear state to InitState
    object Init : GameAction()

    object GetGameId : GameAction()

    //read gameId from WEB DB or local db
    data class StartGame(val gameId: Int, val judgeId: Int) : GameAction()

    data class EndOfRound(val votedPlayer: List<Int>): GameAction()

    //make REST GET to recap gameID
    data class EndGame(
        val gameId: Int,
        val typeEnd: GameEndType?,
        val winnerTeam: WinnerTeamType?,
        val playersKilled: List<Int>,
        val playersVoted: List<Int>,
        val bestMove: List<Int>
    ) : GameAction()
}

enum class GameEndType {
    DEFAULT, CLEAR, PPK
}

enum class WinnerTeamType {
    RED, BLACk, DRAW
}