package com.turbosokol.iqmafiaapp.features.judge.players

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgePlayersReducer : Reducer<JudgePlayersState> {
    override fun reduce(oldState: JudgePlayersState, action: Action): JudgePlayersState {
        return when (action) {
            is JudgePlayersAction.Init -> {
                JudgePlayersState.getInitState()
            }

            is JudgePlayersAction.UpdateNickNames -> {
                oldState.copy(nickNames = action.nickNames)
            }

            is JudgePlayersAction.UpdateCharacterCards -> {
                oldState.copy(characterCards = action.characterCards)
            }

            is JudgePlayersAction.UpdateVoteNominations -> {
                oldState.copy(voteNomination = action.voteNomination)
            }

            else -> oldState
        }
    }
}