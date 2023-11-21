package com.turbosokol.iqmafiaapp.features.judge.analytics.players

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class PlayersReducer : Reducer<PlayersState> {
    override fun reduce(oldState: PlayersState, action: Action): PlayersState {
        return when (action) {
            is PlayersAction.Init -> {
                PlayersState.getInitState()
            }

            is PlayersAction.UpdateNickNames -> {
                oldState.copy(nickNames = action.nickNames)
            }

            is PlayersAction.UpdateProfilesInfo -> {
                oldState.copy(allProfilesFromBE = action.allProfilesFromBE)
            }

            is PlayersAction.UpdateCharacterCards -> {
                oldState.copy(characterCards = action.characterCards)
            }

            is PlayersAction.UpdateVoteNominations -> {
                oldState.copy(voteNomination = action.voteNomination)
            }

            else -> oldState
        }
    }
}