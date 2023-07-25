package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class JudgeCardsScreenState(
    val isInit: Boolean,
    var isHidden: Boolean,
    var listIndex: Int,
    val cardsList: List<CharacterCardModel>
) : GeneralState {

    companion object {
        fun getInitState(): JudgeCardsScreenState = JudgeCardsScreenState(
            isInit = true,
            isHidden = true, listIndex = -1,
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

}//конец JudgeCardsScreenState

sealed class JudgeCardsScreenAction : Action {
    //set isInit = true, set isHidden = true, set new randomised list of cards
    object Init : JudgeCardsScreenAction()//Init тут - название

    //set isInit = false, set isHidden = !oldstate.isHidden, set listIndex = if(oldstate.isHidden) olstate.count+1
    object ShowNext : JudgeCardsScreenAction()
}

