package com.turbosokol.iqmafiaapp.features.judge.screens.night

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class NightScreenState (
//    MARK:: fetch actual state in VIEW LAYER
//    val playersState: PlayersState,

    val isCharacterShown: List<Boolean>,
    val playersKilled: List<Int>,
    val bestVote: List<Int>,
    val isFirstNight: Boolean
    ) : GeneralState {
    companion object {
        // WHEN isCharacterShown -> ROLE with same index IS NOT HIDDEN, button edit character is VISIBLE and role can be reassigned
        // WHEN isFirstNight -> button edit character is VISIBLE and role can be reassigned AND HINT FOR ROLE EDITING IS SHOWN
        fun getInitState(): NightScreenState = NightScreenState(
            isCharacterShown = listOf(false, false, false, false, false, false, false, false, false, false),
            playersKilled = emptyList(),
            bestVote = listOf(0, 0, 0),
            isFirstNight = true
        )
    }
}

sealed class NightScreenAction: Action {
    object Init: NightScreenAction()

    // WHEN isCharacterShown -> ROLE with same index IS NOT HIDDEN, button edit character is VISIBLE and role can be reassigned
    data class ShowCharacterCard(val isCharacterShown: List<Boolean>): NightScreenAction()

    // MARK:: implement UI response with sorted list of night kills
    data class KillSomeone(val playersKilled: List<Int>): NightScreenAction()
    data class SetBestVote(val bestVote: List<Int>): NightScreenAction()
    object SwitchFirstNightFlag: NightScreenAction()
}
