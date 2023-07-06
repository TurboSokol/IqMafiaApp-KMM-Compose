package com.turbosokol.iqmafiaapp.util

import kotlin.math.max

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

const val NEIGHBOR_MAX_ATTEMPTS = 3
const val BLOCK_SIZE = 10

fun tournamentShuffleSlots(players: List<String>, games: Int, onDone:(List<List<String>>) -> Unit) {
    val results = mutableListOf<List<String>>()
    val numberOfBlocks = (games + BLOCK_SIZE - 1) / BLOCK_SIZE

    for (block in 0 until numberOfBlocks) {
        for (gameIndex in 0 until BLOCK_SIZE) {
            var shuffledPlayers = players.shuffled()
            while (!uniquePositionCheck(shuffledPlayers, gameIndex, results)) {
                shuffledPlayers = players.shuffled()
            }

            var neighborAttemptCounter = 0
            while (neighborAttemptCounter < NEIGHBOR_MAX_ATTEMPTS && !uniqueNeighborCheck(shuffledPlayers, results)) {
                shuffledPlayers = players.shuffled()
                neighborAttemptCounter++
            }

            results.add(shuffledPlayers)
            if (results.size == games) {
                onDone(results)
                break
            }
        }
        if (results.size == games) {
            onDone(results)
            break
        }
    }
}

fun uniquePositionCheck(
    currentGame: List<String>,
    gameIndex: Int,
    previousGames: List<List<String>>
): Boolean {
    val startIndex = max(0, previousGames.size - BLOCK_SIZE)
    val subList = previousGames.subList(startIndex, previousGames.size)

    subList.forEach { game ->
        game.forEachIndexed { position, player ->
            if (currentGame[position] == player) return false
        }
    }

    return true
}

fun uniqueNeighborCheck(currentGame: List<String>, previousGames: List<List<String>>): Boolean {
    if (previousGames.isEmpty()) return true

    val lastGame = previousGames.last()
    return currentGame.zipWithNext().none { (player1, player2) ->
        lastGame.zipWithNext().any { (lastPlayer1, lastPlayer2) ->
            (player1 == lastPlayer1 && player2 == lastPlayer2) || (player1 == lastPlayer2 && player2 == lastPlayer1)
        }
    }
}