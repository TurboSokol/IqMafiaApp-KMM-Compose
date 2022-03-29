package com.turbosokol.iqmafiaapp.core.di

import com.turbosokol.iqmafiaapp.common.app.AppMiddleware
import com.turbosokol.iqmafiaapp.common.app.AppReducer
import com.turbosokol.iqmafiaapp.common.app.AppState
import com.turbosokol.iqmafiaapp.common.app.RootReducer
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.ReduxStore
import com.turbosokol.iqmafiaapp.core.redux.Store
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    //modules
}

val storeModule = module {
    single<Store<AppState, Action, Effect>> {
        ReduxStore(
            reducer = RootReducer(
                appReducer = get()
            ),
            AppState(get()),
            listOf(
                AppMiddleware()
            )
        )
    }
}