package com.turbosokol.iqmafiaapp.common.app

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Reducer

class RootReducer (
    private val appReducer: AppReducer
): Reducer<AppState> {
    override fun reduce(oldState: AppState, action: Action): AppState =
        appReducer
            .reduce(oldState, action)
            .copy(

            )
}