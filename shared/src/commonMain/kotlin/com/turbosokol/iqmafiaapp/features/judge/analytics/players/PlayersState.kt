package com.turbosokol.iqmafiaapp.features.judge.analytics.players

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.data.profile.ProfileUIModel

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class PlayersState(
    val profiles: List<ProfileUIModel>,
    val characterCards: List<CharacterCardModel>,
    val voteNomination: List<Boolean>,
    val allProfilesFromBE: List<ProfileUIModel>
) : GeneralState {
    companion object {

        // by default we have 10 red players, player slot = list.index+1
        fun getInitState(): PlayersState = PlayersState(
            profiles = listOf(ProfileUIModel(nickName = "Player1"),
                ProfileUIModel(nickName = "Player2"),
                ProfileUIModel(nickName = "Player3"),
                ProfileUIModel(nickName = "Player4"),
                ProfileUIModel(nickName = "Player5"),
                ProfileUIModel(nickName = "Player6"),
                ProfileUIModel(nickName = "Player7"),
                ProfileUIModel(nickName = "Player8"),
                ProfileUIModel(nickName = "Player9"),
                ProfileUIModel(nickName = "Player10")
                ),
//            profileIds = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
//            userIds = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            characterCards = listOf(
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.DON),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.BLACK),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.BLACK),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.SHERIFF),
            ),
            voteNomination = listOf(false, false, false, false, false, false, false, false, false, false),
            allProfilesFromBE = listOf()
        )

    }
}

sealed class PlayersAction: Action {
    object Init: PlayersAction()
    data class UpdateNickNames(val nickNames: List<String>): PlayersAction()

    //get all profiles from BACK END
    data object GetProfilesFromBE: PlayersAction()

    //PROFILE IDs used for hold nickname, USER IDs used for hold auth info and admins permissions
    data class UpdateProfilesInfo(val allProfilesFromBE: List<ProfileUIModel>): PlayersAction()
    data class UpdateCharacterCards(val characterCards: List<CharacterCardModel>): PlayersAction()

    data class UpdateVoteNominations(val voteNomination: List<Boolean>): PlayersAction()

    object ProfileUIModel : PlayersAction()

}
