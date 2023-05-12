package com.turbosokol.iqmafiaapp.features.judge.screens.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeSlotsScreenReducer : Reducer<JudgeSlotsScreenState> {
    override fun reduce(oldState: JudgeSlotsScreenState, action: Action): JudgeSlotsScreenState {
        return when (action) {
            is JudgeSlotsScreenAction.Init -> {
                oldState.copy(
                    isInit = true,
                    isHidden = true,
                    listIndex = -1,
                    slotsList = action.slotsList
                )
            }

            is JudgeSlotsScreenAction.ShowNext -> {
                oldState.copy(
                    isInit = false,
                    isHidden = !oldState.isHidden,
                    listIndex = if (oldState.isHidden) oldState.listIndex + 1 else oldState.listIndex
                )
            }

            else -> oldState
        }
    }

}