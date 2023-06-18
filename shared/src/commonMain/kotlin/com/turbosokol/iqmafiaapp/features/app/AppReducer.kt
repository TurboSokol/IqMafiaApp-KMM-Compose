package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthReducer
import com.turbosokol.iqmafiaapp.features.judge.game.JudgeGameReducer
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersReducer
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievementsScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenReducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class AppReducer : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is AppAction.SetPlatform -> {
                oldState.copy(platform = action.platform)
            }

            else -> oldState
        }
    }

}

class RootReducer(
    private val appReducer: AppReducer,
    private val authReducer: AuthReducer,
    private val judgeSlotsScreenReducer: JudgeSlotsScreenReducer,
    private val judgeCardsScreenReducer: JudgeCardsScreenReducer,
    private val judgeDayScreenReducer: JudgeDayScreenReducer,
    private val judgeNightScreenReducer: JudgeNightScreenReducer,
    private val judgeScoreScreenReducer: JudgeScoreScreenReducer,
    private val judgeAchievementsScreenReducer: JudgeAchievementsScreenReducer,
    private val judgeGameReducer: JudgeGameReducer,
    private val judgePlayersReducer: JudgePlayersReducer,
    private val judgeRoundReducer: JudgeRoundReducer
) : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState = appReducer
        .reduce(oldState, action)
        .copy(
            authState = authReducer.reduce(oldState.authState, action),
            judgeSlotsScreenState = judgeSlotsScreenReducer.reduce(oldState.judgeSlotsScreenState, action),
            judgeCardsScreenState = judgeCardsScreenReducer.reduce(oldState.judgeCardsScreenState, action),
            judgeDayScreenState = judgeDayScreenReducer.reduce(oldState.judgeDayScreenState, action),
            judgeNightScreenState = judgeNightScreenReducer.reduce(oldState.judgeNightScreenState, action),
            judgeScoreScreenState = judgeScoreScreenReducer.reduce(oldState.judgeScoreScreenState, action),
            judgeAchievementScreenState = judgeAchievementsScreenReducer.reduce(oldState.judgeAchievementScreenState, action),
            judgeGameState = judgeGameReducer.reduce(oldState.judgeGameState, action),
            judgePlayersState = judgePlayersReducer.reduce(oldState.judgePlayersState, action),
            judgeRoundState = judgeRoundReducer.reduce(oldState.judgeRoundState, action)
        )
}