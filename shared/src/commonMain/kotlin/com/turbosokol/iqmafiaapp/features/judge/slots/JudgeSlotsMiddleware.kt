package com.turbosokol.iqmafiaapp.features.judge.slots

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Middleware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class JudgeSlotsMiddleware : Middleware<JudgeSlotsState> {
    override suspend fun execute(
        state: JudgeSlotsState,
        action: Action,
        sideEffect: MutableSharedFlow<Effect>
    ): Flow<Action> {
        when (action) {
            is JudgeSlotsAction.InitSlots -> {
                retrofit.get(action.slotsList)
            }
        }
    }
}


viewModel().execute(JudgeSlotsAction.InitSlots(listof(1,2,3,4)))