package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

data class AppState(
    val lifecycle: AppLifecycle = AppLifecycle.FOREGROUND,
    val platform: AppPlatform = AppPlatform.NOT_SET,

    internal val authState: AuthState = AuthState.getInitialDefault(),
    internal val accountState: AccountState = AccountState.getInitialDefault(),
    internal val loginState: LoginState = LoginState.getInitialDefault(),
): GeneralState {
    fun getAuthState() = authState
    fun getAccountState() = accountState
    fun getLoginState() = loginState
}

sealed class AppAction: Action {
    data class SetLifecycle(val lifecycle: AppLifecycle): AppAction()
    data class SetPlatform(val platform: AppPlatform): AppAction()
}

enum class AppLifecycle {FOREGROUND, BACKGROUND}
enum class AppPlatform {NOT_SET, ANDROID, IOS}
