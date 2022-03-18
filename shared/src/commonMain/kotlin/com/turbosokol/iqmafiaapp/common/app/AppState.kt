package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.core.redux.GeneralState

data class AppState(
    internal val authState: AuthState = AuthState.getInitialDefault(),
    internal val accountState: AccountState = AccountState.getInitialDefault(),
    internal val loginState: LoginState = LoginState.getInitialDefault(),
): GeneralState {
    fun getAuthState() = authState
    fun getAccountState() = accountState
    fun getLoginState() = loginState
}
