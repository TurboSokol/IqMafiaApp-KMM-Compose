package com.turbosokol.iqmafiaapp.features.judge.day

import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class JudgeDayState(
    val playersNames: List<String>,
    val playersFaults: List<Int>,
    val votedNominations: List<Int>
) : GeneralState {
    companion object {
        fun getInitState(): JudgeDayState = JudgeDayState(

        )
    }
}