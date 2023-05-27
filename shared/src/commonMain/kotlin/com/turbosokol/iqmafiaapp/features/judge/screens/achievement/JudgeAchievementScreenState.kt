package com.turbosokol.iqmafiaapp.features.judge.screens.achievement

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.achievement.AchievementModel
import com.turbosokol.iqmafiaapp.data.achievement.AchievementType
import com.turbosokol.iqmafiaapp.data.achievement.PersonalAchievementsList

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class JudgeAchievementScreenState(
    //list of ten players,
    //each player may have several achievements
    val playersAchievements: List<PersonalAchievementsList>,
    val playersAchievementsSumm: List<Double>

) : GeneralState {
    companion object {

        fun getInitState(): JudgeAchievementScreenState = JudgeAchievementScreenState(

            //list index == player slot
            //each player can have few bonus
            playersAchievements = listOf(
                //player 1
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 2
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 3
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 4
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 5
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 6
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 7
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 8
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 9
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
                        AchievementModel(
                            type = AchievementType.AUTO_BONUS,
                            price = 0.3,
                            comment = "Auto bonus"
                        )
                    )
                ),
                //player 10
                PersonalAchievementsList(
                    personalAchievementsList = listOf(
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
            playersAchievementsSumm = listOf(0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3)
        )
    }
}

sealed class JudgeAchievementScreenAction : Action {
    object Init : JudgeAchievementScreenAction()
    data class AddPersonalAchievement(val playersAchievements: List<PersonalAchievementsList>) :
        JudgeAchievementScreenAction()
}


