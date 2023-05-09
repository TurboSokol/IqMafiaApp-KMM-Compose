package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthState
import com.turbosokol.iqmafiaapp.features.judge.cards.JudgeCardsState
import com.turbosokol.iqmafiaapp.features.judge.slots.JudgeSlotsState
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class AppState(
    val platform: AppPlatform = AppPlatform.EMPTY,
    internal val authState: AuthState = AuthState.getInitState(),
    internal val navigationState: NavigationState = NavigationState.getInitState(),
    internal val judgeSlotsState: JudgeSlotsState = JudgeSlotsState.getInitState(),
    internal val judgeCardsState: JudgeCardsState = JudgeCardsState.getInitState(),
    internal val judgeDayState: JudgeDayState = JudgeDayState.getInitState(),
    internal val judgeNightState: JudgeNightState = JudgeNightState.getInitState(),
    internal val judgeScoreState: JudgeScoreState = JudgeScoreState.getInitValue()
) : GeneralState {

    fun getAuthState() = authState
    fun getNavigationState() = navigationState
    fun getJudgeSlotsState() = judgeSlotsState
    fun getJudgeCardsState() = judgeCardsState
    fun getJudgeDayState() = judgeDayState
    fun getJudgeNightState() = judgeNightState
    fun getJudgeScoreState() = judgeScoreState

}

sealed class AppAction : Action {
    data class SetPlatform(val platform: AppPlatform) : AppAction()
}

enum class AppPlatform { EMPTY, ANDROID, IOS }