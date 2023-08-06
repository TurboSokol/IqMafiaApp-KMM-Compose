package com.turbosokol.iqmafiaapp.features.judge.screens.night

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class NightScreenReducer : Reducer<NightScreenState> {
    override fun reduce(oldState: NightScreenState, action: Action): NightScreenState {
        return when (action) {

            is NightScreenAction.Init -> {
                NightScreenState.getInitState()
            }

            // WHEN isCharacterShown -> ROLE with same index IS NOT HIDDEN,
            // button edit character is VISIBLE and role CAN be reassigned by user
            is NightScreenAction.ShowCharacterCard -> {
                oldState.copy(isCharacterShown = action.isCharacterShown)
            }

            //listOf slots killed player, FIRST of them enable to SetBestVote
            is NightScreenAction.KillSomeone -> {
                oldState.copy(playersKilled = action.playersKilled)
            }

            //AFTER first night kill implement UI response for SetBestMove
            //define 3 black characters
            is NightScreenAction.SetBestVote -> {
                oldState.copy(bestVote = action.bestVote)
            }

            // WHEN isFirstNight -> button edit character is VISIBLE and role can be reassigned
            // HINT FOR ROLE EDITING IS SHOWN
            is NightScreenAction.SwitchFirstNightFlag -> {
                oldState.copy(isFirstNight = false)
            }

            else -> oldState
        }
    }
}