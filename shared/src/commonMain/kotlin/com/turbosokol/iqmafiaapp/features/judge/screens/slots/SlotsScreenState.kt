package com.turbosokol.iqmafiaapp.features.judge.screens.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class SlotsScreenState(
    val isInit: Boolean,
    val isHidden: Boolean,
    val listIndex: Int,
    val slotsList: List<Int>,
    //in extended mode user can set 10 names and game count in tournament and get slots for all games
    val isTourMode: Boolean,
    val tourPlayersNames: List<String>,
    val tourGamesCount: Int,
    val tourSlotsList: List<List<String>>,
    val isResetDialogVisible: Boolean,
    val inProgress: Boolean
) : GeneralState {

    companion object {
        fun getInitState(): SlotsScreenState = SlotsScreenState(
            isInit = true,
            isHidden = true,
            listIndex = -1,
            slotsList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).shuffled(),
            isTourMode = false,
            tourPlayersNames = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
            tourGamesCount = 10,
            tourSlotsList = emptyList(),
            isResetDialogVisible = false,
            inProgress = false
        )
    }

}

sealed class SlotsScreenAction : Action {
    //set isInit = true, set isHidden = true, set new randomised list of slots
    data class Init(val isTourMode: Boolean) : SlotsScreenAction()
    //set isInit = false, set isHidden = !oldstate.isHidden, set listIndex = if(oldstate.isHidden) olstate.count+1
    object ShowNext : SlotsScreenAction()
    object SetIsTourMode : SlotsScreenAction()
    data class SetTourPlayers(val tourPlayersNames: List<String>) : SlotsScreenAction()
    data class SetTourGamesCount(val tourGamesCount: Int) : SlotsScreenAction()
    data class SetProgress(val inProgress: Boolean) : SlotsScreenAction()
    data class SetTourSlotsList(val tourSlotsList: List<List<String>>) : SlotsScreenAction()
    object SetResetDialogVisible : SlotsScreenAction()
}
