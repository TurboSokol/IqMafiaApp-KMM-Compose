
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
import com.turbosokol.iqmafiaapp.features.judge.game.JudgeGameMiddleware
import com.turbosokol.iqmafiaapp.features.judge.game.JudgeGameReducer
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersMiddleware
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersReducer
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundMiddleware
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievesScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievesScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenReducer
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenMiddleware
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenReducer
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
                judgeSlotsScreenReducer = get(),
                judgeCardsScreenReducer = get(),
                judgeDayScreenReducer = get(),
                judgeNightScreenReducer = get(),
                judgeScoreScreenReducer = get(),
                judgeAchievesScreenReducer = get(),
                judgeGameReducer = get(),
                judgePlayersReducer = get(),
                judgeRoundReducer = get()
            ),
            defaultValue = AppState(),
            middlewares = listOf(
                AppMiddleware(),
                AuthMiddleware(),
                NavigationMiddleware(),
                AccountMiddleware(),
                JudgeSlotsScreenMiddleware(),
                JudgeCardsScreenMiddleware(),
                JudgeDayScreenMiddleware(),
                JudgeNightScreenMiddleware(),
                JudgeScoreScreenMiddleware(),
                JudgeAchievesScreenMiddleware(),
                JudgeGameMiddleware(),
                JudgePlayersMiddleware(),
                JudgeRoundMiddleware()
            )
        )
    }

    single { AppReducer() }
    single { AuthReducer() }
    single { NavigationReducer() }
    single { AccountReducer() }
    single { JudgeSlotsScreenReducer() }
    single { JudgeCardsScreenReducer() }
    single { JudgeDayScreenReducer() }
    single { JudgeNightScreenReducer() }
    single { JudgeScoreScreenReducer() }
    single { JudgeAchievesScreenReducer() }
    single { JudgeGameReducer() }
    single { JudgePlayersReducer() }
    single { JudgeRoundReducer() }
}