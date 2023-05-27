package com.turbosokol.iqmafiaapp.features.judge.screens.achievement

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeAchievementsScreenReducer : Reducer<JudgeAchievementScreenState> {
    override fun reduce(
        oldState: JudgeAchievementScreenState,
        action: Action
    ): JudgeAchievementScreenState {
        return when (action) {
            is JudgeAchievementScreenAction.Init -> {
                JudgeAchievementScreenState.getInitState()
            }

            is JudgeAchievementScreenAction.AddPersonalAchievement -> {
                val newPlayersSummList = emptyList<Double>()
                action.playersAchievements.forEach { personalAchievementsList ->
                    val personalAchSumm = 0.0
                    newPlayersSummList.plus(personalAchievementsList.personalAchievementsList.forEach {
                        personalAchSumm.plus(it.price)
                    })

                    newPlayersSummList.plus(personalAchSumm)
                }

                oldState.copy(
                    playersAchievements = action.playersAchievements,
                    playersAchievementsSumm = newPlayersSummList
                )
            }

            else -> oldState
        }
    }
}