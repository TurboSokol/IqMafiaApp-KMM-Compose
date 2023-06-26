package com.turbosokol.iqmafiaapp.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQBottomNavItemView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.achievement.JudgeAchievesScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.night.JudgeNightScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.score.JudgeScoreScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationAction
import com.turbosokol.iqmafiaapp.features.navigation.redux.NavigationState
import com.turbosokol.iqmafiaapp.icons.PlayingCardsIcon
import com.turbosokol.iqmafiaapp.screens.AchieveScreenView
import com.turbosokol.iqmafiaapp.screens.CardsScreenView
import com.turbosokol.iqmafiaapp.screens.DayScreenView
import com.turbosokol.iqmafiaapp.screens.NightScreenView
import com.turbosokol.iqmafiaapp.screens.ScoreScreenView
import com.turbosokol.iqmafiaapp.screens.SlotsScreenView
import com.turbosokol.iqmafiaapp.theme.Colors
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

    val selectedTab = when (navigationState.currentScreenState) {
        is JudgeSlotsScreenState -> NavigationTabs.SLOTS
        is JudgeCardsScreenState -> NavigationTabs.CARDS
        is JudgeAchievesScreenState -> NavigationTabs.ACHIEVEMENT
        is JudgeDayScreenState -> NavigationTabs.DAY
        is JudgeNightScreenState -> NavigationTabs.NIGHT
        is JudgeScoreScreenState -> NavigationTabs.SCORE
        else -> NavigationTabs.DAY
    }

    IqMafiaAppTheme {
        Scaffold(
            bottomBar = {
                Surface(
                    shape = RoundedCornerShape(topStart = Dimensions.CornerRadius.medium, topEnd = Dimensions.CornerRadius.medium),
                    elevation = Dimensions.Elevation.medium
                )  {
                    BottomNavigation(backgroundColor = Colors.skyBlue) {
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
                                NavigationTabs.SLOTS -> Triple("Slots", Icons.Outlined.Chair, NavigationAction.JudgeSlotsScreen(appState.getJudgeSlotsState()))
                                NavigationTabs.CARDS -> Triple("Cards", Icons.Outlined.PlayingCardsIcon, NavigationAction.JudgeCardsScreen(appState.getJudgeCardsState()))
                                NavigationTabs.ACHIEVEMENT -> Triple("Achieve", Icons.Outlined.WorkspacePremium, NavigationAction.JudgeAchievementScreen(appState.getJudgeAchievementScreenState()))
                                NavigationTabs.DAY -> Triple("Day", Icons.Outlined.WbSunny, NavigationAction.JudgeDayScreen(appState.getJudgeDayState()))
                                NavigationTabs.NIGHT -> Triple("Night", Icons.Outlined.NightsStay, NavigationAction.JudgeNightsScreen(appState.getJudgeNightState()))
                                NavigationTabs.SCORE -> Triple("Score", Icons.Outlined.Insights, NavigationAction.JudgeScoreScreen(appState.getJudgeScoreState()))
                            }

                            IQBottomNavItemView(
                                modifier = Modifier,
                                icon = { Icon(imageVector = icon, contentDescription = null) },
                                label = { Text(text = title) },
                                selected = selectedTab == tab,
                                alwaysShowLabel = true,
                                onClick = { viewModel.execute(action) },
                                selectedContentColor = Colors.red,
                                unselectedContentColor = Colors.darkGrey51,
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
    SLOTS, CARDS, ACHIEVEMENT, DAY, NIGHT, SCORE
}
