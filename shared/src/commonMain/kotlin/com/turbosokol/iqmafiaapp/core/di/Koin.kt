
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.ReduxStore
import com.turbosokol.iqmafiaapp.core.redux.Store
import com.turbosokol.iqmafiaapp.features.account.AccountMiddleware
import com.turbosokol.iqmafiaapp.features.account.AccountReducer
import com.turbosokol.iqmafiaapp.features.app.AppMiddleware
import com.turbosokol.iqmafiaapp.features.app.AppReducer
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.app.RootReducer
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthMiddleware
import com.turbosokol.iqmafiaapp.features.auth.redux.AuthReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameMiddleware
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersMiddleware
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersReducer
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundMiddleware
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenReducer
import com.turbosokol.iqmafiaapp.features.navigation.NavigationMiddleware
import com.turbosokol.iqmafiaapp.features.navigation.NavigationReducer
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
fun initSharedKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(storeModule)
}

@ExperimentalTime
val storeModule = module {
    single<Store<AppState, Action, Effect>> {
        ReduxStore(
            reducer = RootReducer(
                appReducer = get(),
                authReducer = get(),
                navigationReducer = get(),
                accountReducer = get(),
                slotsScreenReducer = get(),
                cardsScreenReducer = get(),
                dayScreenReducer = get(),
                nightScreenReducer = get(),
                scoreScreenReducer = get(),
                achievesScreenReducer = get(),
                gameReducer = get(),
                playersReducer = get(),
                roundReducer = get()
            ),
            defaultValue = AppState(),
            middlewares = listOf(
                AppMiddleware(),
                AuthMiddleware(),
                NavigationMiddleware(),
                AccountMiddleware(),
                SlotsScreenMiddleware(),
                CardsScreenMiddleware(),
                DayScreenMiddleware(),
                NightScreenMiddleware(),
                ScoreScreenMiddleware(),
                AchievesScreenMiddleware(),
                GameMiddleware(),
                PlayersMiddleware(),
                RoundMiddleware()
            )
        )
    }

    single { AppReducer() }
    single { AuthReducer() }
    single { NavigationReducer() }
    single { AccountReducer() }
    single { SlotsScreenReducer() }
    single { CardsScreenReducer() }
    single { DayScreenReducer() }
    single { NightScreenReducer() }
    single { ScoreScreenReducer() }
    single { AchievesScreenReducer() }
    single { GameReducer() }
    single { PlayersReducer() }
    single { RoundReducer() }
}