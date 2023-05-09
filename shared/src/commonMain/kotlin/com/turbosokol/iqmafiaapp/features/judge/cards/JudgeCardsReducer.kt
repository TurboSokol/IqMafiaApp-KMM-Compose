package com.turbosokol.iqmafiaapp.features.judge.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.judge.slots.JudgeSlotsAction
import com.turbosokol.iqmafiaapp.features.judge.slots.JudgeSlotsState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeCardsReducer : Reducer<JudgeCardsState> {
    override fun reduce(oldState: JudgeCardsState, action: Action): JudgeCardsState {
        return when (action) {
            is JudgeCardsAction.InitCards -> {
                oldState.copy(
                    isInit = true,
                    isHidden = true,
                    listIndex = -1,
                    cardsList = action.cardsList
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