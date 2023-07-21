package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeCardsScreenReducer : Reducer<JudgeCardsScreenState> {
    override fun reduce(oldState: JudgeCardsScreenState, action: Action): JudgeCardsScreenState {
        return when (action) {
            is JudgeCardsScreenAction.Init -> {
                JudgeCardsScreenState.getInitState()
//                oldState.copy(
//                    isInit = true,
//                    isHidden = true,
//                    listIndex = -1,
//                    cardsList = action.cardsList
//                )
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