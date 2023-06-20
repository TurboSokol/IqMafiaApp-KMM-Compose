package com.turbosokol.iqmafiaapp.features.judge.screens.achievement

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.achievement.AchievementModel
import com.turbosokol.iqmafiaapp.data.achievement.AchievementType
import com.turbosokol.iqmafiaapp.data.achievement.PersonalAchievesList

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class JudgeAchievesScreenState(
    //list of ten players,
    //each player may have several achievements
    val playersAchieves: List<PersonalAchievesList>,
    val playersAchievesSumm: List<Double>

) : GeneralState {
    companion object {

        fun getInitState(): JudgeAchievesScreenState = JudgeAchievesScreenState(

            //list index == player slot
            //each player can have few bonus
            playersAchieves = listOf(
                //player 1
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 2
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 3
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 4
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 5
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 6
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 7
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 8
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 9
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 10
                PersonalAchievesList(
                    personalAchievesList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                )
            ),


            //list index == player slot,
            //each player have final summ of achievements (bonus points)
            //playersPointsSumm calculates from playersPersonalAchievements
            playersAchievesSumm = listOf(0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3)
        )
    }
}

sealed class JudgeAchievesScreenAction : Action {
    object Init : JudgeAchievesScreenAction()
    data class AddPersonalAchieves(val playersAchieves: List<PersonalAchievesList>) :
        JudgeAchievesScreenAction()
}


