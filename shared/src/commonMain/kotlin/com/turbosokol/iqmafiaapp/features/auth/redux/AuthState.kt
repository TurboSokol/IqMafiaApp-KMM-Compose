package com.turbosokol.iqmafiaapp.features.auth.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

data class AuthState(
    val inProgress: Boolean,
    val isUserAuthenticated: Boolean,
    val userId: Int?
) : GeneralState {
    companion object {
        fun getInitState(): AuthState = AuthState(inProgress = false, isUserAuthenticated = false, userId = null)
    }
}

sealed class AuthAction: Action {
    object CheckUserAuth : AuthAction()
    object ExecuteUserAuth: AuthAction()
    data class UserSuccessAuthenticated(val userId: Int) : AuthAction()
}