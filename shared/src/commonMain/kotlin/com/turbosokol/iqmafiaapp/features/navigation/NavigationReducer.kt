package com.turbosokol.iqmafiaapp.features.navigation

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationAction
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class NavigationReducer : Reducer<NavigationState> {
    override fun reduce(oldState: NavigationState, action: Action): NavigationState {
        return when (action) {
            is NavigationAction.JudgeSlotsScreen -> {
                oldState.copy(currentScreenState = action.slotsScreenState)
            }

            is NavigationAction.JudgeCardsScreen -> {
                oldState.copy(currentScreenState = action.cardsScreenState)
            }

            is NavigationAction.JudgeDayScreen -> {
                oldState.copy(currentScreenState = action.dayScreenState)
            }

            is NavigationAction.JudgeNightsScreen -> {
                oldState.copy(currentScreenState = action.nightScreenState)
            }

            is NavigationAction.JudgeScoreScreen -> {
                oldState.copy(currentScreenState = action.scoreScreenState)
            }

            is NavigationAction.JudgeAchievementScreen -> {
                oldState.copy(currentScreenState = action.achievementScreenState)
            }

            else -> oldState
        }
    }
}