package com.turbosokol.iqmafiaapp.features.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthReducer
import com.turbosokol.iqmafiaapp.features.judge.slots.JudgeSlotsReducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class AppReducer: Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState {
        return when (action) {
            is AppAction.SetPlatform -> {
                oldState.copy(platform = action.platform)
            }
            else -> oldState
        }
    }

}

class RootReducer(
    private val appReducer: AppReducer,
    private val authReducer: AuthReducer,
    private val judgeSlotsReducer: JudgeSlotsReducer
) : Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState = appReducer
        .reduce(oldState, action)
        .copy(
            authState = authReducer.reduce(oldState.authState, action),
            judgeSlotsState = judgeSlotsReducer.reduce(oldState.judgeSlotsState, action)
        )
}