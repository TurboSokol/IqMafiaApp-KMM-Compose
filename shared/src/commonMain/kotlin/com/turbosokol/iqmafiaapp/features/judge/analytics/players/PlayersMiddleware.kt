package com.turbosokol.iqmafiaapp.features.judge.analytics.players

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import com.turbosokol.iqmafiaapp.data.profile.ProfileMapper.toUIList
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.repository.network.MainNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class PlayersMiddleware(private val mainNetworkApi: MainNetworkApi) : Middleware<AppState> {
    override suspend fun execute(
        state: AppState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
        return when (action) {
            is PlayersAction.Init -> emptyFlow()
            is PlayersAction.UpdateProfiles -> emptyFlow()
            is PlayersAction.GetProfilesFromBE -> flow {
                if (state.playersState.allProfilesFromBE.isEmpty()) {
                    val result = mainNetworkApi.getPlayersProfiles()
                    if (result.success) {
                        emit(PlayersAction.UpdateAllProfiles(result.data?.toUIList() ?: emptyList()))
                    }
                }
            }

            is PlayersAction.UpdateAllProfiles -> emptyFlow()
            is PlayersAction.UpdateCharacterCards -> emptyFlow()
            is PlayersAction.UpdateVoteNominations -> emptyFlow()
            else -> emptyFlow()
        }
    }
}