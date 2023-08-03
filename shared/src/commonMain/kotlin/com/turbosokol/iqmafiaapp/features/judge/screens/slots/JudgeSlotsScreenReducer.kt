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
                if (action.isTourMode) JudgeSlotsScreenState.getInitState().copy(isTourMode = true) else JudgeSlotsScreenState.getInitState()
            }

            is JudgeSlotsScreenAction.ShowNext -> {
                oldState.copy(
                    isInit = false,
                    isHidden = !oldState.isHidden,
                    listIndex = if (oldState.isHidden) oldState.listIndex + 1 else oldState.listIndex
                )
            }

            is JudgeSlotsScreenAction.SetIsTourMode -> {
                oldState.copy(isTourMode = !oldState.isTourMode)
            }

            is JudgeSlotsScreenAction.SetTourPlayers -> {
                oldState.copy(tourPlayersNames = action.tourPlayersNames)
            }

            is JudgeSlotsScreenAction.SetTourGamesCount -> {
                oldState.copy(tourGamesCount = action.tourGamesCount)
            }

            is JudgeSlotsScreenAction.SetTourSlotsList -> {
                oldState.copy(tourSlotsList = action.tourSlotsList, inProgress = if (action.tourSlotsList.isEmpty()) true else false)
            }

            is JudgeSlotsScreenAction.SetProgress -> {
                oldState.copy(inProgress = action.inProgress)
            }

            is JudgeSlotsScreenAction.SetResetDialogVisible -> {
                oldState.copy(isResetDialogVisible = !oldState.isResetDialogVisible)
            }

            else -> oldState
        }
    }

}