package com.turbosokol.iqmafiaapp.data.profile

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

object ProfileMapper {
    fun PlayersProfileBEModel.toUIList(): List<ProfileUIModel> {
        return this.data?.map { profileData ->
            ProfileUIModel(
                id = profileData.id?: 0,
                nickName = profileData.nickName?: "null"
            )
        }?: emptyList()
    }
}