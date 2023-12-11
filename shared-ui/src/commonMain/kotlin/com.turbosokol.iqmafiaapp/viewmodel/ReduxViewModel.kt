package com.turbosokol.iqmafiaapp.viewmodel

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Store
import com.turbosokol.iqmafiaapp.features.app.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.brightify.hyperdrive.multiplatformx.BaseViewModel

class ReduxViewModel(
    val store: Store<AppState, Action, Effect>
): BaseViewModel() {
    fun execute(action: Action) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            store.dispatch(action)
        }
    }
}