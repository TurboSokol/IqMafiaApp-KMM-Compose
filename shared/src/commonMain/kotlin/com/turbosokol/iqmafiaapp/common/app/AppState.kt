package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.common.account.redux.AccountState
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.GeneralState
import com.turbosokol.iqmafiaapp.judge.game.redux.GameState
import com.turbosokol.iqmafiaapp.judge.player.redux.PlayersState

data class AppState(
    val platform: AppPlatform = AppPlatform.NOT_SET,

    //TODO::Wait for firebase auth integration
    //internal val authState: AuthState = AuthState.getDefaultState(),
    //internal val loginState: LoginState = LoginState.getDefaultState(),
    internal val accountState: AccountState = AccountState.getDefaultState(),
    internal val gameState: GameState = GameState.getDefaultState(),
    internal val playersState: PlayersState = PlayersState.getDefaultState()
) : GeneralState {
    //TODO::Wait for firebase auth integration
    //fun getAuthState() = authState
    //fun getLoginState() = loginState
    fun getAccountState() = accountState
    fun getGameState() = gameState
    fun getPlayersState() = playersState
}

sealed class AppAction : Action {
    data class SetPlatform(val platform: AppPlatform) : AppAction()
}

enum class AppPlatform { NOT_SET, ANDROID, IOS }
