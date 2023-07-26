package com.turbosokol.iqmafiaapp.features.judge.screens.cards

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeCardsScreenReducer : Reducer<JudgeCardsScreenState> {
    override fun reduce(oldState: JudgeCardsScreenState, action: Action): JudgeCardsScreenState {


        return when (action) {
            is JudgeCardsScreenAction.ShowNext -> {

                    oldState.copy(
                    isHidden =
                    !oldState.isHidden,
                    listIndex =
                     if(oldState.isHidden && oldState.listIndex == 0) {oldState.listIndex}
                     else   if(oldState.isHidden) {oldState.listIndex +1}
                    else{oldState.listIndex}
                    )
            }

            is JudgeCardsScreenAction.Init -> {
                JudgeCardsScreenState.getInitState()
            }

            else -> oldState
        }
    }

}

//val isInit: Boolean,
//var isHidden: Boolean,
//var listIndex: Int,
//val cardsList: List<CharacterCardModel>