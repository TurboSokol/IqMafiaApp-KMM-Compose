package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class CardsScreenState(
    val isInit: Boolean,
    val isHidden: Boolean,
    val listIndex: Int,
    val isResetDialogVisible: Boolean,
    val cardsList: List<CharacterCardModel>
) : GeneralState {

    companion object {
        fun getInitState(): CardsScreenState = CardsScreenState(
            isInit = true, isHidden = true, listIndex = -1, isResetDialogVisible = false,
            cardsList = listOf(
                CharacterCardModel(type = CharacterCardType.DON),
                CharacterCardModel(type = CharacterCardType.BLACK),
                CharacterCardModel(type = CharacterCardType.BLACK),
                CharacterCardModel(type = CharacterCardType.SHERIFF),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED),
                CharacterCardModel(type = CharacterCardType.RED)
            ).shuffled()
        )
    }

}

sealed class CardsScreenAction : Action {
    //set isInit = true, set isHidden = true, set new randomised list of cards
    object Init : CardsScreenAction()

    //set isInit = false, set isHidden = !oldstate.isHidden, set listIndex = if(oldstate.isHidden) olstate.count+1
    object ShowNext : CardsScreenAction()

    object SetResetDialogVisible : CardsScreenAction()
}