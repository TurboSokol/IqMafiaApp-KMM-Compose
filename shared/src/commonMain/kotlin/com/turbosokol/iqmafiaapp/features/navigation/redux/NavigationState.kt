package com.turbosokol.iqmafiaapp.features.navigation.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class NavigationState(
    val currentScreen: AppScreen
) : GeneralState {

    companion object {
        fun getInitState(): NavigationState = NavigationState(currentScreen = AppScreen.JudgeSlotsScreen(
            JudgeSlotsScreenState.getInitState()))
    }

}

sealed class NavigationAction : Action {

}

sealed class AppScreen {
    data class JudgeSlotsScreen(val slotsScreenState: JudgeSlotsScreenState): AppScreen()
    data class JudgeCardsScreen(val cardsScreenState: JudgeCardsScreenState): AppScreen()
    data class JudgeDayScreen(val dayScreenState: JudgeDayScreenState): AppScreen()
    data class JudgeNightsScreen(val nightScreenState: JudgeNightScreenState): AppScreen()
    data class JudgeScoreScreen(val scoreScreenState: JudgeScoreScreenState): AppScreen()
    data class JudgeAchievementScreen(val achievementScreen: JudgeAchievementScreenState): AppScreen()
}