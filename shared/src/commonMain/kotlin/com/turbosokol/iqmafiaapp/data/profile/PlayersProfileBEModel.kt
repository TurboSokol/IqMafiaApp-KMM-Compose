package com.turbosokol.iqmafiaapp.data.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Serializable
data class PlayersProfileBEModel(
    @SerialName("data")
    val data: List<ProfileData>?
) {
    @Serializable
    data class ProfileData(
        val id: Int,
        @SerialName("user_id")
        val userId: Int,
        @SerialName("nickname")
        val nickName: String
    )
}
