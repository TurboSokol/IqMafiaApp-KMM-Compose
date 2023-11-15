package com.turbosokol.iqmafiaapp.data.game

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

data class GamePutRequestModel(
    val game: SendGameBEModel,
    val profiles: List<SendProfilesBEModel>
) {
    data class SendGameBEModel(
        val gameJudgeId: Int = 76,
        // "default", "clear", "ppk"
        val typeEnd: String,
        // "red", "black", "draw"
        val winnerTeam: String,
        // System.time
        val playedAt: String
    )

    data class SendProfilesBEModel(
        val profileId: Int? = null,
        //"red", "black", "sherif", "don"
        val characterCard: String,
        val slot: Int,
        val bestMove: List<Int>? = null,
        val score: Int,
        val scoreBonus: Float
    )
}


