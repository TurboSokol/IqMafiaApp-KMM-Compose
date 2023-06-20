package com.turbosokol.iqmafiaapp.features.navigation.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievementScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class NavigationState(
    val currentScreenState: GeneralState
) : GeneralState {

    companion object {
        fun getInitState(): NavigationState = NavigationState(currentScreenState = JudgeSlotsScreenState.getInitState())
    }

}

sealed class NavigationAction: Action {
    data class JudgeSlotsScreen(val slotsScreenState: JudgeSlotsScreenState): NavigationAction()
    data class JudgeCardsScreen(val cardsScreenState: JudgeCardsScreenState): NavigationAction()
    data class JudgeDayScreen(val dayScreenState: JudgeDayScreenState): NavigationAction()
    data class JudgeNightsScreen(val nightScreenState: JudgeNightScreenState): NavigationAction()
    data class JudgeScoreScreen(val scoreScreenState: JudgeScoreScreenState): NavigationAction()
    data class JudgeAchievementScreen(val achievementScreenState: JudgeAchievementScreenState): NavigationAction()
}