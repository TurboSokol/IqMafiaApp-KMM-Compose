package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

class AppReducer: Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is AppAction.SetLifecycle -> {
                oldState.copy(lifecycle = action.lifecycle)
            }
            is AppAction.SetPlatform -> {
                oldState.copy(platform = action.platform)
            }
            else -> oldState
        }
    }
}