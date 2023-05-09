package com.turbosokol.iqmafiaapp.features.judge.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/
data class JudgeSlotsState(
    val isInit: Boolean,
    val isHidden: Boolean,
    val listIndex: Int,
    val slotsList: List<Int>
): GeneralState {

    companion object {
        fun getInitState(): JudgeSlotsState = JudgeSlotsState(isInit = true, isHidden = true, listIndex = -1, slotsList = listOf(1,2,3,4,5,6,7,8,9,10))
    }

}

sealed class JudgeSlotsAction: Action {
    //set isInit = true, set isHidden = true, set new randomised list of slots
    data class InitSlots(val slotsList: List<Int>): JudgeSlotsAction()

    //set isInit = false, set isHidden = !oldstate.isHidden, set listIndex = if(oldstate.isHidden) olstate.count+1
    object ShowNext: JudgeSlotsAction()
}
