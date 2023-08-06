package com.turbosokol.iqmafiaapp.features.judge.screens.achievement

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
class AchievesScreenReducer : Reducer<AchievesScreenState> {
    override fun reduce(
        oldState: AchievesScreenState,
        action: Action
    ): AchievesScreenState {
        return when (action) {
            is AchievesScreenAction.Init -> {
                AchievesScreenState.getInitState()
            }

            is AchievesScreenAction.AddPersonalAchieves -> {
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