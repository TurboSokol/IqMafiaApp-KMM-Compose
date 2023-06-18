package com.turbosokol.iqmafiaapp.judge.game.model

data class Game(
    val gameId: Int,
    val gameStarted: Boolean,
    val gameEnded: Boolean,
    val teamWon: String,
    val firstKill: Int
)