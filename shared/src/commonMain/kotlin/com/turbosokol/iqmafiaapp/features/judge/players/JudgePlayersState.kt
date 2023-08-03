package com.turbosokol.iqmafiaapp.features.judge.players

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgePlayersState(
    val nickNames: List<String>,
    val profileIds: List<Int>,
    val userIds: List<Int>,
    val characterCards: List<CharacterCardModel>,
    val voteNomination: List<Boolean>
) : GeneralState {
    companion object {

        // by default we have 10 red players, player slot = list.index+1
        fun getInitState(): JudgePlayersState = JudgePlayersState(
            nickNames = listOf(
                "Player 1",
                "Player 2",
                "Player 3",
                "Player 4",
                "Player 5",
                "Player 6",
                "Player 7",
                "Player 8",
                "Player 9",
                "Player 10"
            ),
            profileIds = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            userIds = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            characterCards = listOf(
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED)
            ),
            voteNomination = listOf(false, false, false, false, false, false, false, false, false, false)
        )

    }
}

sealed class JudgePlayersAction: Action {
    object Init: JudgePlayersAction()
    data class UpdateNickNames(val nickNames: List<String>): JudgePlayersAction()

    //PROFILE IDs used for hold nickname, USER IDs used for hold auth info and admins permissions
    data class UpdateProfilesInfo(val profileIds: List<Int>, val userIds: List<Int>): JudgePlayersAction()
    data class UpdateCharacterCards(val characterCards: List<CharacterCardModel>): JudgePlayersAction()

    data class UpdateVoteNominations(val voteNomination: List<Boolean>): JudgePlayersAction()

}
