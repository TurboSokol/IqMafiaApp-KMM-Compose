package com.turbosokol.iqmafiaapp.features.judge.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class JudgeCardsState(
    val isInit: Boolean,
    val isHidden: Boolean,
    val listIndex: Int,
    val cardsList: List<CharacterCardModel>
) : GeneralState {

    companion object {
        fun getInitState(): JudgeCardsState = JudgeCardsState(
            isInit = true, isHidden = true, listIndex = -1, cardsList = listOf(
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
            )
        )
    }

}

sealed class JudgeCardsAction : Action {
    //set isInit = true, set isHidden = true, set new randomised list of cards
    data class InitCards(val cardsList: List<CharacterCardModel>) : JudgeCardsAction()

    //set isInit = false, set isHidden = !oldstate.isHidden, set listIndex = if(oldstate.isHidden) olstate.count+1
    object ShowNext : JudgeCardsAction()
}
