package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.account.AccountReducer
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenReducer
import com.turbosokol.iqmafiaapp.features.navigation.NavigationReducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class AppReducer : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is AppAction.SetPlatform -> {
                oldState.copy(appPlatform = action.platform)
            }

            else -> oldState
        }
    }

}

class RootReducer(
    private val appReducer: AppReducer,
    private val authReducer: AuthReducer,
    private val navigationReducer: NavigationReducer,
    private val accountReducer: AccountReducer,
    private val slotsScreenReducer: SlotsScreenReducer,
    private val cardsScreenReducer: CardsScreenReducer,
    private val dayScreenReducer: DayScreenReducer,
    private val nightScreenReducer: NightScreenReducer,
    private val scoreScreenReducer: ScoreScreenReducer,
    private val achievesScreenReducer: AchievesScreenReducer,
    private val gameReducer: GameReducer,
    private val playersReducer: PlayersReducer,
    private val roundReducer: RoundReducer
) : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState = appReducer
        .reduce(oldState, action)
        .copy(
            authState = authReducer.reduce(oldState.authState, action),
            navigationState = navigationReducer.reduce(oldState.navigationState, action),
            accountState = accountReducer.reduce(oldState.accountState, action),
            slotsScreenState = slotsScreenReducer.reduce(oldState.slotsScreenState, action),
            cardsScreenState = cardsScreenReducer.reduce(oldState.cardsScreenState, action),
            dayScreenState = dayScreenReducer.reduce(oldState.dayScreenState, action),
            nightScreenState = nightScreenReducer.reduce(oldState.nightScreenState, action),
            scoreScreenState = scoreScreenReducer.reduce(oldState.scoreScreenState, action),
            achievesScreenState = achievesScreenReducer.reduce(oldState.achievesScreenState, action),
            gameState = gameReducer.reduce(oldState.gameState, action),
            playersState = playersReducer.reduce(oldState.playersState, action),
            roundState = roundReducer.reduce(oldState.roundState, action)
        )
}