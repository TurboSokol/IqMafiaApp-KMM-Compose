package com.turbosokol.iqmafiaapp.features.judge.analytics.game

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.game.GamePutRequestModel.SendGameBEModel
import com.turbosokol.iqmafiaapp.data.game.GamePutRequestModel.SendProfilesBEModel
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
    val mainPoints: List<Int>,
    val comments: List<String>
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
            dopPoints = listOf(0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3),
            mainPoints = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            comments = listOf("","","","","","","","","","")
        )
    }
}

sealed class GameAction : Action {
    //clear state to InitState
    data object Init : GameAction()
    data object GetGameId : GameAction()
    data class UpdateMainPoints(val mainPoints: List<Int>) : GameAction()
    data class UpdateDopPoints(val dopPoints: List<Double>) : GameAction()
    data class UpdateSummaryPoints(val summaryPoints: List<Double>) : GameAction()
    data class UpdateComments(val comments: List<String>) : GameAction()
    data class EndOfRound(val votedPlayer: List<Int>) : GameAction()
    data class GamePutRequestModel(val game: SendGameBEModel, val profiles: List<SendProfilesBEModel>) : GameAction()

    //read gameId from WEB DB or local db
    data class StartGame(val gameId: Int, val judgeId: Int) : GameAction()


    //make REST GET to recap gameID
    data class EndGame(
        val gameId: Int,
        val typeEnd: GameEndType?,
        val winnerTeam: WinnerTeamType?,
        val playersKilled: List<Int>,
        val playersVoted: List<Int>,
        val bestMove: List<Int>,
        val summaryPoints: List<Double>,
        val dopPoints: List<Double>,
        val mainPoints: List<Int>,
        val comments: List<String>
    ) : GameAction()
}

enum class GameEndType {
    DEFAULT, CLEAR, PPK
}

enum class WinnerTeamType {
    RED, BLACk, DRAW
}