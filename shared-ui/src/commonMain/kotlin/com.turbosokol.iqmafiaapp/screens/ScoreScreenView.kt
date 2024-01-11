package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQScoreRow
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersState
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun ScoreScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val playersState: MutableState<PlayersState> = remember { mutableStateOf(appState.getPlayersState()) }
    val gameState: MutableState<GameState> = remember { mutableStateOf(appState.getGameState()) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.background(MaterialTheme.colorScheme.onBackground).fillMaxWidth(1f),
            elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium),
        ) {

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
                    .padding(
                        horizontal = Dimensions.Padding.small,
                        vertical = Dimensions.Padding.micro
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.scoreNumberSymbol,
                    fontSize = Dimensions.TextSize.small,
                    modifier = Modifier.weight(0.1f)
                )


                Text(
                    text = Strings.scoreNamesHeader,
                    fontSize = Dimensions.TextSize.small,
                    modifier = Modifier.weight(0.3f)
                )

                Text(
                    text = Strings.scoreCardRate,
                    fontSize = Dimensions.TextSize.small,
                    modifier = Modifier.weight(0.15f)
                )

                Text(
                    text = Strings.scoreDops,
                    fontSize = Dimensions.TextSize.small,
                    modifier = Modifier.weight(0.2f)
                )

                Text(
                    text = Strings.comment,
                    fontSize = Dimensions.TextSize.small,
                    modifier = Modifier.weight(0.2f)
                )

                Button(modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
                    .padding(start = Dimensions.Padding.xsmall),
                    contentPadding = PaddingValues(
                        start = 2.dp,
                        top = 0.dp,
                        end = 2.dp,
                        bottom = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    onClick = {/* no-op */ }
                )
                {
                    Text(text = "Save")
                }


            }
            playersState.value.profiles.forEachIndexed { profileIndex, profile ->
                IQScoreRow(
                    modifier = Modifier.background(color = //Here is the colors of player names bg
                    when (playersState.value.characterCards[profileIndex].type) {
                        CharacterCardType.RED -> Colors.red
                        CharacterCardType.DON -> Color.LightGray
                        CharacterCardType.SHERIFF -> Color.Yellow
                        CharacterCardType.BLACK -> Color.DarkGray
                        else -> Color.Cyan
                    }
                    ),
                    slot = profileIndex + 1,
                    playerName = profile.nickName,
                    playerNameColor = when (playersState.value.characterCards[profileIndex].type) {
                        CharacterCardType.RED -> Color.White
                        CharacterCardType.DON -> Color.Black
                        CharacterCardType.SHERIFF -> Color.Black
                        CharacterCardType.BLACK -> Color.White
                        else -> Color.Cyan
                    },
                    mainScore = gameState.value.mainPoints[profileIndex],
                    onMainPointsChanged = { newPoints ->
                        viewModel.execute(GameAction.UpdateMainPoints(gameState.value.mainPoints.mapIndexed { index, points ->
                            if (index == profileIndex) newPoints else points
                        }))
                    },
                    dopPoints = gameState.value.dopPoints[profileIndex],
                    onDopsChanged = { changedDops ->
                        viewModel.execute(GameAction.UpdateDopPoints(gameState.value.dopPoints.mapIndexed { index, dops ->
                            if (profileIndex == index) changedDops else dops
                        }))
                    },
                    comment = gameState.value.comments[profileIndex],
                    onCommentChanged = { newComment ->
                        viewModel.execute(GameAction.UpdateComments(gameState.value.comments.mapIndexed { index, comment ->
                            if (profileIndex == index) newComment else comment
                        }))
                    },
                    allProfilesFromBE = playersState.value.allProfilesFromBE,
                    profile = playersState.value.profiles[profileIndex],
                    onProfileChanged = {
                        changedProfile ->
                        viewModel.execute(
                            PlayersAction.UpdateProfiles(playersState.value.profiles.mapIndexed
                            { newProfileIndex, newProfile ->
                                if (newProfileIndex == profileIndex) changedProfile else newProfile
                            })
                        )



                    }
                )
            }
        }
    }
}
