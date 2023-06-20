package com.turbosokol.iqmafiaapp.data.achievement

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class AchievementModel(
    val type: AchievementType,
    val price: Double,
    val comment: String
)

data class PersonalAchievesList(
    val personalAchievesList: List<AchievementModel>
)

enum class AchievementType {
    BONUS, VOTE, NOMINATION, BEST_MOVE, AUTO_BONUS, NEGATIVE
}