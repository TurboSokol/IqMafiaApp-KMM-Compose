package com.turbosokol.iqmafiaapp.android

import androidx.lifecycle.ViewModel
import com.turbosokol.iqmafiaapp.common.app.AppState
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Store

class ReduxViewModel(
    val store: Store<AppState, Action, Effect>
): ViewModel() {
    fun execute(action: Action) {
        store.execute(action)
    }
}