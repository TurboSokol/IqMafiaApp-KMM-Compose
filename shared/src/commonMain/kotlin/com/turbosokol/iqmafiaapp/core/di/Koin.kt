package com.turbosokol.iqmafiaapp.core.di

import com.turbosokol.iqmafiaapp.common.app.*
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.ReduxStore
import com.turbosokol.iqmafiaapp.core.redux.Store
import com.turbosokol.iqmafiaapp.judge.game.redux.GameMiddleware
import com.turbosokol.iqmafiaapp.judge.game.redux.GameReducer
import com.turbosokol.iqmafiaapp.judge.player.redux.PlayerMiddleware
import com.turbosokol.iqmafiaapp.judge.player.redux.PlayerReducer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
@ExperimentalTime
@InternalSerializationApi
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(storeModule)
}

@ExperimentalTime
val storeModule = module {
    single<Store<AppState, Action, Effect>> {
        ReduxStore(
            reducer = RootReducer(
                appReducer = get(),
                gameReducer = get(),
                playerReducer = get()
            ),
            defaultState = AppState(),
            middlewares = listOf(
                AppMiddleware(),
                GameMiddleware(),
                PlayerMiddleware()
            )
        )
    }

    single { AppReducer() }
    single { GameReducer() }
    single { PlayerReducer() }
}