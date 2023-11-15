package com.turbosokol.iqmafiaapp.repository.network

import com.turbosokol.iqmafiaapp.data.game.GamePutRequestModel
import com.turbosokol.iqmafiaapp.data.network.ApiResponse
import com.turbosokol.iqmafiaapp.data.network.ApiResponseEmpty
import com.turbosokol.iqmafiaapp.data.profile.PlayersProfileBEModel

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

interface MainNetworkApi {
 suspend fun getPlayersProfiles(): ApiResponse<PlayersProfileBEModel>
 suspend fun putGameWithProfiles(gamePutRequestModel: GamePutRequestModel): ApiResponseEmpty
}