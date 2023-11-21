package com.turbosokol.iqmafiaapp.repository.network

import com.turbosokol.iqmafiaapp.data.game.GamePutRequestModel
import com.turbosokol.iqmafiaapp.data.core.ApiResponse
import com.turbosokol.iqmafiaapp.data.core.ApiResponseEmpty
import com.turbosokol.iqmafiaapp.data.profile.PlayersProfileBEModel
import com.turbosokol.iqmafiaapp.service.KtorWebService

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

class MainNetworkApiImpl(private val client: KtorWebService) : MainNetworkApi {
    override suspend fun getPlayersProfiles(): ApiResponse<PlayersProfileBEModel> {
        val response = client.makeJsonGet<PlayersProfileBEModel>(endpoint = "/api/profiles")
        if (!response.success) {
            response.errorResponse?.message = "getPlayersProfile failed"
        }
        return response
    }

    override suspend fun putGameWithProfiles(gamePutRequestModel: GamePutRequestModel): ApiResponseEmpty {
        TODO("Not yet implemented")
    }
}