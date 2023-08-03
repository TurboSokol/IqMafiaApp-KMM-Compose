package com.turbosokol.iqmafiaapp.util

import kotlin.math.max
import kotlin.random.Random

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

const val NEIGHBOR_MAX_ATTEMPTS = 3
const val BLOCK_SIZE = 10

fun tournamentShuffleSlots(players: List<String>, games: Int): List<List<String>> {
    val results = mutableListOf<List<String>>()
    val numberOfBlocks = (games + BLOCK_SIZE - 1) / BLOCK_SIZE

    for (block in 0 until numberOfBlocks) {
        for (gameIndex in 0 until BLOCK_SIZE) {
            var shuffledPlayers = players.shuffled()
            var attemptCounter = 0
            val unUniquePosition = uniquePositionCheck(shuffledPlayers, results)
            while (uniquePositionCheck(shuffledPlayers, results) != -1 && attemptCounter < 10) {
                val firstSub = if (unUniquePosition < 5) shuffledPlayers.subList(0, 5).shuffled() else shuffledPlayers.subList(0, 5)
                val secondSub = if (unUniquePosition > 4) shuffledPlayers.subList(5, shuffledPlayers.lastIndex+1).shuffled() else shuffledPlayers.subList(5, shuffledPlayers.lastIndex+1)
                shuffledPlayers = firstSub.plus(secondSub)
                attemptCounter++
            }

            if (games <= 20) {
                var neighborAttemptCounter = 0
                while (neighborAttemptCounter < NEIGHBOR_MAX_ATTEMPTS && !uniqueNeighborCheck(shuffledPlayers, results)) {
                    shuffledPlayers = players.shuffled()
                    neighborAttemptCounter++
                }
            }

            results.add(shuffledPlayers)
            if (results.size == games) {
                break
            }
        }
        if (results.size == games) {
            break
        }
    }
    return results
}

fun uniquePositionCheck(
    currentGame: List<String>,
    previousGames: List<List<String>>
): Int {
    val startIndex = max(0, previousGames.size - BLOCK_SIZE)
    val subList = previousGames.subList(startIndex, previousGames.size)

    subList.forEach { game ->
        game.forEachIndexed { position, player ->
            if (currentGame[position] == player) return position
        }
    }

    return -1
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