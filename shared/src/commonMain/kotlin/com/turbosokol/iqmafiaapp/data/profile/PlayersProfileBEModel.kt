package com.turbosokol.iqmafiaapp.data.profile

import kotlinx.serialization.SerialName

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class PlayersProfileBEModel(
    val data: List<ProfileData>?
) {
    data class ProfileData(
        val id: Int,
        @SerialName("user_id")
        val userId: Int,
        @SerialName("nickname")
        val nickName: String
    )
}
