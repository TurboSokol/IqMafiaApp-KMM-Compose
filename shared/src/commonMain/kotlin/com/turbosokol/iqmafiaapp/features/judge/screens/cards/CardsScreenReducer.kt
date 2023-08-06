package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class CardsScreenReducer : Reducer<CardsScreenState> {
    override fun reduce(oldState: CardsScreenState, action: Action): CardsScreenState {
        return when (action) {
            is CardsScreenAction.Init -> {
                CardsScreenState.getInitState()
            }

            is CardsScreenAction.ShowNext -> {
                oldState.copy(
                    isInit = false,
                    isHidden = !oldState.isHidden,
                    listIndex = if (oldState.isHidden) { oldState.listIndex + 1 } else { oldState.listIndex }
                )
            }

            is CardsScreenAction.SetResetDialogVisible -> {
                oldState.copy(isResetDialogVisible = !oldState.isResetDialogVisible)
            }

            else -> oldState
        }
    }
}