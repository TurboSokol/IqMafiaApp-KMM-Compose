package com.turbosokol.iqmafiaapp.judge.player.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.judge.player.model.Player

data class PlayersState(
    val player1: Player,
    val player2: Player,
    val player3: Player,
    val player4: Player,
    val player5: Player,
    val player6: Player,
    val player7: Player,
    val player8: Player,
    val player9: Player,
    val player10: Player
) : GeneralState {
    companion object {
        fun getDefaultState(): PlayersState {
            return PlayersState(
                player1 = Player(
                    playerId = 0,
                    slot = 1,
                    name = "Player 1",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player2 = Player(
                    playerId = 0,
                    slot = 2,
                    name = "Player 2",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player3 = Player(
                    playerId = 0,
                    slot = 3,
                    name = "Player 3",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player4 = Player(
                    playerId = 0,
                    slot = 4,
                    name = "Player 4",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player5 = Player(
                    playerId = 0,
                    slot = 5,
                    name = "Player 5",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player6 = Player(
                    playerId = 0,
                    slot = 6,
                    name = "Player 6",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player7 = Player(
                    playerId = 0,
                    slot = 7,
                    name = "Player 7",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player8 = Player(
                    playerId = 0,
                    slot = 8,
                    name = "Player 8",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player9 = Player(
                    playerId = 0,
                    slot = 9,
                    name = "Player 9",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                ),
                player10 = Player(
                    playerId = 0,
                    slot = 10,
                    name = "Player 10",
                    falls = 0,
                    bestMove = emptyList<Int>(),
                    score = 0f
                )
            )
        }
    }
}

sealed class PlayerAction : Action {
    object InitPlayers : PlayerAction()

    data class EditPlayerName(val slot: Int, val name: String) : PlayerAction()
    data class EditPlayerFalls(val slot: Int, val falls: Int) : PlayerAction()
    data class EditBestMove(val slot: Int, val bestMove: List<Int>) : PlayerAction()
    data class EditScore(val slot: Int, val score: Float) : PlayerAction()
}

data class FallsEffect(val slot: Int, val falls: Int): Effect