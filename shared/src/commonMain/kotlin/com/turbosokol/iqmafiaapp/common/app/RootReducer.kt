package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer
import com.turbosokol.iqmafiaapp.judge.game.redux.GameReducer
import com.turbosokol.iqmafiaapp.judge.player.redux.PlayerReducer

class RootReducer (
    private val appReducer: AppReducer,
    private val gameReducer: GameReducer,
    private val playerReducer: PlayerReducer
): Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState =
        appReducer
            .reduce(oldState, action)
            .copy(
                gameState = gameReducer.reduce(oldState.gameState, action),
                playersState = playerReducer.reduce(oldState.playersState, action)

            )
}