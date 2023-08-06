package com.turbosokol.iqmafiaapp.features.judge.screens.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class SlotsScreenReducer : Reducer<SlotsScreenState> {
    override fun reduce(oldState: SlotsScreenState, action: Action): SlotsScreenState {
        return when (action) {
            is SlotsScreenAction.Init -> {
                if (action.isTourMode) SlotsScreenState.getInitState().copy(isTourMode = true) else SlotsScreenState.getInitState()
            }

            is SlotsScreenAction.ShowNext -> {
                oldState.copy(
                    isInit = false,
                    isHidden = !oldState.isHidden,
                    listIndex = if (oldState.isHidden) oldState.listIndex + 1 else oldState.listIndex
                )
            }

            is SlotsScreenAction.SetIsTourMode -> {
                oldState.copy(isTourMode = !oldState.isTourMode)
            }

            is SlotsScreenAction.SetTourPlayers -> {
                oldState.copy(tourPlayersNames = action.tourPlayersNames)
            }

            is SlotsScreenAction.SetTourGamesCount -> {
                oldState.copy(tourGamesCount = action.tourGamesCount)
            }

            is SlotsScreenAction.SetTourSlotsList -> {
                oldState.copy(tourSlotsList = action.tourSlotsList, inProgress = if (action.tourSlotsList.isEmpty()) true else false)
            }

            is SlotsScreenAction.SetProgress -> {
                oldState.copy(inProgress = action.inProgress)
            }

            is SlotsScreenAction.SetResetDialogVisible -> {
                oldState.copy(isResetDialogVisible = !oldState.isResetDialogVisible)
            }

            else -> oldState
        }
    }

}