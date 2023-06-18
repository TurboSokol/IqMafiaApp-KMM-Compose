package com.turbosokol.iqmafiaapp.judge.player.model

data class Player(
    val playerId: Int,
    val slot: Int,
    val name: String,
    val falls: Int,
    val bestMove: List<Int>,
    val score: Float
)
