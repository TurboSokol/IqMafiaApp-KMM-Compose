package com.turbosokol.iqmafiaapp.features.judge.screens.day

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class DayScreenReducer : Reducer<DayScreenState> {
    override fun reduce(oldState: DayScreenState, action: Action): DayScreenState {
        return when (action) {
            is DayScreenAction.Init -> {
                DayScreenState.getInitState()
            }

            //add or remove player faults
            //player slot = playerFaults.index
            //faults count = int at index
            is DayScreenAction.UpdateFaults -> {
                oldState.copy(playersFaults = action.playersFaults)
            }

            else -> oldState
        }
    }
}