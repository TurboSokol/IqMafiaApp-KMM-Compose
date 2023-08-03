package com.turbosokol.iqmafiaapp.viewmodel

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.Store
import com.turbosokol.iqmafiaapp.features.app.AppState
import org.brightify.hyperdrive.multiplatformx.BaseViewModel

class ReduxViewModel(
    //Store - интерфейс, который принимает три интерфейса.
    val store: Store<AppState, Action, Effect>
): BaseViewModel() { //Base - для кроссплатформы
    fun execute(action: Action) {
        store.dispatch(action)
    }
}