package com.turbosokol.iqmafiaapp.features.auth.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

class AuthReducer : Reducer<AuthState> {
    override fun reduce(oldState: AuthState, action: Action): AuthState {
        return when (action) {
            is AuthAction.ExecuteUserAuth -> {
                oldState.copy(inProgress = true, isUserAuthenticated = false)
            }

            is AuthAction.UserSuccessAuthenticated -> {
                oldState.copy(inProgress = false, isUserAuthenticated = true, userId = action.userId)
            }

            else -> oldState
        }
    }

}