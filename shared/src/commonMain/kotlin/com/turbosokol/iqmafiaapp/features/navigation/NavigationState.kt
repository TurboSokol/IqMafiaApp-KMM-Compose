package com.turbosokol.iqmafiaapp.features.navigation.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class NavigationState(
    val currentScreenState: GeneralState
) : GeneralState {

    companion object {
        fun getInitState(): NavigationState = NavigationState(currentScreenState = SlotsScreenState.getInitState())
    }

}

sealed class NavigationAction: Action {
    data class SlotsScreen(val slotsScreenState: SlotsScreenState): NavigationAction()
    data class CardsScreen(val cardsScreenState: CardsScreenState): NavigationAction()
    data class DayScreen(val dayScreenState: DayScreenState): NavigationAction()
    data class NightsScreen(val nightScreenState: NightScreenState): NavigationAction()
    data class ScoreScreen(val scoreScreenState: ScoreScreenState): NavigationAction()
    data class AchievementScreen(val achievementScreenState: AchievesScreenState): NavigationAction()
}