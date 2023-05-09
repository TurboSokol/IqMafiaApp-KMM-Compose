package com.turbosokol.iqmafiaapp.features.judge.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeSlotsReducer : Reducer<JudgeSlotsState> {
    override fun reduce(oldState: JudgeSlotsState, action: Action): JudgeSlotsState {
        return when (action) {
            is JudgeSlotsAction.InitSlots -> {
                oldState.copy(
                    isInit = true,
                    isHidden = true,
                    listIndex = -1,
                    slotsList = action.slotsList
                )
            }

            is JudgeSlotsAction.ShowNext -> {
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