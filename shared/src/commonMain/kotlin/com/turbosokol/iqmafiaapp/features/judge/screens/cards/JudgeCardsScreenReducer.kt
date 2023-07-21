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
            is JudgeCardsScreenAction.Init -> {
//<<<<<<< HEAD
                JudgeCardsScreenState.getInitState()
//                oldState.copy(
//                    isInit = true,
//                    isHidden = true,
//                    listIndex = -1,
//                    cardsList = action.cardsList
//                )
//=======
                oldState.copy(
                    isInit = true,
                    isHidden = true,
                    listIndex = -1,
//                    cardsList = action.cardsList
                )

                JudgeCardsScreenState(
                    isInit = true, isHidden = true, listIndex = -1,
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
                    )
                )

//>>>>>>> 5896e45ba994fe23576fb26cb5fa4fc0651997b3
            }

            is JudgeSlotsScreenAction.ShowNext -> {
                oldState.copy(
                    isInit = false,
                    isHidden = !oldState.isHidden,
                    listIndex = if (oldState.isHidden) oldState.listIndex + 1 else oldState.listIndex
                )
            }

            else -> oldState
        }
    }

}