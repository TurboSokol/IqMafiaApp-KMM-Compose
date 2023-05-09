package com.turbosokol.iqmafiaapp.features.navigation.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.judge.cards.JudgeCardsState
import com.turbosokol.iqmafiaapp.features.judge.slots.JudgeSlotsState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class NavigationState(
    val currentScreen: AppScreen
) : GeneralState {

    companion object {
        fun getInitState(): NavigationState = NavigationState(currentScreen = AppScreen.JudgeSlotsScreen(JudgeSlotsState.getInitState()))
    }

}

sealed class NavigationAction : Action {

}

sealed class AppScreen {
    data class JudgeSlotsScreen(val slotsState: JudgeSlotsState): AppScreen()
    data class JudgeCardsScreen(val cardsState: JudgeCardsState): AppScreen()
    data class JudgeDayScreen(val datState: JudgeDayState): AppScreen()
    data class JudgeNightsScreen(val cardsState: JudgeNightState): AppScreen()
    data class JudgeScoreScreen(val scoreState: JudgeScoreState): AppScreen()
}