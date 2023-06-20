package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.account.AccountState
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenState
import com.turbosokol.iqmafiaapp.features.judge.game.JudgeGameState
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersState
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundState
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievementScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class AppState(
    val appPlatform: AppPlatform = AppPlatform.EMPTY,
    internal val authState: AuthState = AuthState.getInitState(),
    internal val navigationState: NavigationState = NavigationState.getInitState(),
    internal val accountState: AccountState = AccountState.getInitState(),

    internal val judgeSlotsScreenState: JudgeSlotsScreenState = JudgeSlotsScreenState.getInitState(),
    internal val judgeCardsScreenState: JudgeCardsScreenState = JudgeCardsScreenState.getInitState(),
    internal val judgeDayScreenState: JudgeDayScreenState = JudgeDayScreenState.getInitState(),
    internal val judgeNightScreenState: JudgeNightScreenState = JudgeNightScreenState.getInitState(),
    internal val judgeScoreScreenState: JudgeScoreScreenState = JudgeScoreScreenState.getInitState(),
    internal val judgeAchievementScreenState: JudgeAchievementScreenState = JudgeAchievementScreenState.getInitState(),

    internal val judgePlayersState: JudgePlayersState = JudgePlayersState.getInitState(),
    internal val judgeGameState: JudgeGameState = JudgeGameState.getInitState(),
    internal val judgeRoundState: JudgeRoundState = JudgeRoundState.getInitState(),
) : GeneralState {

    fun getAuthState() = authState
    fun getNavigationState() = navigationState
    fun getAccountState() = accountState

    fun getJudgePlayersState() = judgePlayersState
    fun getJudgeGameState() = judgeGameState
    fun getJudgeRoundState() = judgeRoundState

    fun getJudgeSlotsState() = judgeSlotsScreenState
    fun getJudgeCardsState() = judgeCardsScreenState
    fun getJudgeDayState() = judgeDayScreenState
    fun getJudgeNightState() = judgeNightScreenState
    fun getJudgeScoreState() = judgeScoreScreenState
    fun getJudgeAchievementScreenState() = judgeAchievementScreenState
}

sealed class AppAction : Action {
    data class SetPlatform(val platform: AppPlatform) : AppAction()
}

enum class AppPlatform { EMPTY, ANDROID, IOS }