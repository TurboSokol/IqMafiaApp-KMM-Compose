package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.features.account.AccountState
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenState
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersState
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundState
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenState
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

    internal val slotsScreenState: SlotsScreenState = SlotsScreenState.getInitState(),
    internal val cardsScreenState: CardsScreenState = CardsScreenState.getInitState(),
    internal val dayScreenState: DayScreenState = DayScreenState.getInitState(),
    internal val nightScreenState: NightScreenState = NightScreenState.getInitState(),
    internal val scoreScreenState: ScoreScreenState = ScoreScreenState.getInitState(),
    internal val achievesScreenState: AchievesScreenState = AchievesScreenState.getInitState(),

    internal val playersState: PlayersState = PlayersState.getInitState(),
    internal val gameState: GameState = GameState.getInitState(),
    internal val roundState: RoundState = RoundState.getInitState(),
) : GeneralState {

    fun getAuthState() = authState
    fun getNavigationState() = navigationState
    fun getAccountState() = accountState

    fun getPlayersState() = playersState
    fun getGameState() = gameState
    fun getRoundState() = roundState

    fun getSlotsState() = slotsScreenState
    fun getCardsState() = cardsScreenState
    fun getDayState() = dayScreenState
    fun getNightState() = nightScreenState
    fun getScoreState() = scoreScreenState
    fun getAchievementScreenState() = achievesScreenState
}

sealed class AppAction : Action {
    data class SetPlatform(val platform: AppPlatform) : AppAction()
}

enum class AppPlatform { EMPTY, ANDROID, IOS }