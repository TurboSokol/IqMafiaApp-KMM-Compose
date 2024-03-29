package com.turbosokol.iqmafiaapp.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.turbosokol.iqmafiaapp.components.IQBottomNavItemView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.AchievesScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.NightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.ScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenState
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationAction
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationState
import com.turbosokol.iqmafiaapp.icons.PlayingCardsIcon
import com.turbosokol.iqmafiaapp.screens.AchieveScreenView
import com.turbosokol.iqmafiaapp.screens.CardsScreenView
import com.turbosokol.iqmafiaapp.screens.DayScreenView
import com.turbosokol.iqmafiaapp.screens.NightScreenView
import com.turbosokol.iqmafiaapp.screens.ScoreScreenView
import com.turbosokol.iqmafiaapp.screens.SlotsScreenView
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.IqMafiaAppTheme
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow



/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun BottomNavBarView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val navigationState: NavigationState = appState.getNavigationState()
    viewModel.execute(PlayersAction.GetProfilesFromBE)

    val selectedTab = when (navigationState.currentScreenState) {
        is SlotsScreenState -> NavigationTabs.SLOTS
        is CardsScreenState -> NavigationTabs.CARDS
        is AchievesScreenState -> NavigationTabs.ACHIEVEMENT
        is DayScreenState -> NavigationTabs.DAY
        is NightScreenState -> NavigationTabs.NIGHT
        is ScoreScreenState -> NavigationTabs.SCORE
        else -> NavigationTabs.DAY
    }

    val profilesBeTest = appState.getPlayersState().allProfilesFromBE


    IqMafiaAppTheme {
        Scaffold(
            bottomBar = {
                Surface(
                    shape = RoundedCornerShape(topStart = Dimensions.CornerRadius.medium, topEnd = Dimensions.CornerRadius.medium),
                    tonalElevation = Dimensions.Elevation.medium
                )  {
                    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer, windowInsets = WindowInsets(5, 15, 5, 1), modifier = Modifier.height(Dimensions.Components.NavBar.barHeight)
                    ) {

                        val tabsList = listOf<NavigationTabs>(
                            NavigationTabs.SLOTS,
                            NavigationTabs.CARDS,
                            NavigationTabs.ACHIEVEMENT,
                            NavigationTabs.DAY,
                            NavigationTabs.NIGHT,
                            NavigationTabs.SCORE
                        )

                        tabsList.forEach { tab ->
                            val (title, icon, action) = when (tab) {
                                NavigationTabs.SLOTS -> Triple("Slots", Icons.Outlined.Chair, NavigationAction.SlotsScreen(appState.getSlotsState()))
                                NavigationTabs.CARDS -> Triple("Cards", Icons.Outlined.PlayingCardsIcon, NavigationAction.CardsScreen(appState.getCardsState()))
                                NavigationTabs.ACHIEVEMENT -> Triple("Dops", Icons.Outlined.WorkspacePremium, NavigationAction.AchievementScreen(appState.getAchievementScreenState()))
                                NavigationTabs.DAY -> Triple("Day", Icons.Outlined.WbSunny, NavigationAction.DayScreen(appState.getDayState()))
                                NavigationTabs.NIGHT -> Triple("Night", Icons.Outlined.NightsStay, NavigationAction.NightsScreen(appState.getNightState()))
                                NavigationTabs.SCORE -> Triple("Score", Icons.Outlined.Insights, NavigationAction.ScoreScreen(appState.getScoreState()))
                            }

                            IQBottomNavItemView(
                                modifier = Modifier,
                                icon = { Icon(imageVector = icon, contentDescription = null) },
                                label = { Text(text = title, fontSize = Dimensions.Components.NavBar.labelFontSize ) },
                                selected = selectedTab == tab,
                                alwaysShowLabel = true,
                                onClick = { viewModel.execute(action) },
                                selectedContentColor = MaterialTheme.colorScheme.tertiary,
                                unselectedContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (selectedTab) {
                    NavigationTabs.SLOTS -> SlotsScreenView(viewModel)
                    NavigationTabs.CARDS -> CardsScreenView(viewModel)
                    NavigationTabs.ACHIEVEMENT -> AchieveScreenView(viewModel)
                    NavigationTabs.DAY -> DayScreenView(viewModel)
                    NavigationTabs.NIGHT -> NightScreenView(viewModel)
                    NavigationTabs.SCORE -> ScoreScreenView(viewModel)
                }
            }
        }
    }

}

enum class NavigationTabs {
    SLOTS,
    CARDS,
    ACHIEVEMENT,
    DAY,
    NIGHT,
    SCORE
}
