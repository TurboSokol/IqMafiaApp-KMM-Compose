package com.turbosokol.iqmafiaapp.features.judge.screens.achievement

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class JudgeAchievesScreenReducer : Reducer<JudgeAchievesScreenState> {
    override fun reduce(
        oldState: JudgeAchievesScreenState,
        action: Action
    ): JudgeAchievesScreenState {
        return when (action) {
            is JudgeAchievesScreenAction.Init -> {
                JudgeAchievesScreenState.getInitState()
            }

            is JudgeAchievesScreenAction.AddPersonalAchieves -> {
                val newPlayersSummList = emptyList<Double>()
                action.playersAchieves.forEach { personalAchievementsList ->
                    val personalAchSumm = 0.0
                    newPlayersSummList.plus(personalAchievementsList.personalAchievesList.forEach {
                        personalAchSumm.plus(it.price)
                    })

                    newPlayersSummList.plus(personalAchSumm)
                }

                oldState.copy(
                    playersAchieves = action.playersAchieves,
                    playersAchievesSumm = newPlayersSummList
                )
            }

            else -> oldState
        }
    }
}