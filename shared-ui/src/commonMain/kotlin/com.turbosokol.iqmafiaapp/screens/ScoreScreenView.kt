package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.turbosokol.iqmafiaapp.components.IQScoreRow
import com.turbosokol.iqmafiaapp.components.picker.IQScoreSendButton
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.game.GameAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

//import org.jetbrains.skia.Color

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun ScoreScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val playersState = appState.getPlayersState()
    val gameState = appState.getGameState()

    Card(
        elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium),
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        //players info column
        //headers

//        TODO():: BUTTON SEND TO BACK END INTO HEADER
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(Dimensions.Padding.small),

        ) {
            Text(
                text = Strings.scoreNumberSymbol,
                fontSize = Dimensions.TextSize.small,
                modifier = Modifier.weight(0.08f)
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
                modifier = Modifier.weight(0.15f)
            )

            Text(
                text = Strings.comment,
                fontSize = Dimensions.TextSize.small,
                modifier = Modifier.weight(0.32f)
            )


        }

        Column(modifier = Modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            IQScoreSendButton()
            playersState.nickNames.forEachIndexed { playerIndex, name ->
                IQScoreRow(
                    modifier = Modifier,
                    slot = playerIndex + 1,
                    playerColor = when (playersState.characterCards[playerIndex].type) {
                        CharacterCardType.RED -> Colors.red
                        CharacterCardType.DON -> Color.LightGray
                        CharacterCardType.SHERIFF -> Color.Yellow
                        CharacterCardType.BLACK -> Color.DarkGray
                        else -> Color.Cyan
                    },
                    playerName = name,
                    onPlayerNameChanged = { changedText ->
                        val newNames =
                            playersState.nickNames.toMutableList() //slotsState.tourPlayersNames.toMutableList()
                        newNames[playerIndex] = changedText
                        viewModel.execute(PlayersAction.UpdateNickNames(newNames))
                    },
                    playerNameColor = when (playersState.characterCards[playerIndex].type) {
                        CharacterCardType.RED -> Color.White
                        CharacterCardType.DON -> Color.Black
                        CharacterCardType.SHERIFF -> Color.Black
                        CharacterCardType.BLACK -> Color.White
                        else -> Color.Cyan
                    },
                    mainScore = gameState.mainPoints[playerIndex],
                    onMainPointsChanged = { newPoints ->
                        viewModel.execute(GameAction.UpdateMainPoints(gameState.mainPoints.mapIndexed { index, points ->
                            if (index == playerIndex) newPoints else points
                        }))
                    },
                    dopPoints = gameState.dopPoints[playerIndex],
                    onDopsChanged = { changedDops ->
                        viewModel.execute(GameAction.UpdateDopPoints(gameState.dopPoints.mapIndexed { index, dops ->
                            if (playerIndex == index) changedDops else dops
                        }))
                    },
                    comment = gameState.comments[playerIndex],
                    onCommentChanged = { newComment ->
                        viewModel.execute(GameAction.UpdateComments(gameState.comments.mapIndexed { index, comment ->
                            if (playerIndex == index) newComment else comment
                        }))
                    }
                )
            }
//            IQScoreSendButton()
        }
    }


}
