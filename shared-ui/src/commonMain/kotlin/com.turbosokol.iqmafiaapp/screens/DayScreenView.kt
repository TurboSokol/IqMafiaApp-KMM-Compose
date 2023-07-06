package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.turbosokol.iqmafiaapp.components.IQPlayerNameRow
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersAction
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundAction
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
fun DayScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val dayState = appState.getJudgeDayState()
    val judgePlayersState = appState.getJudgePlayersState()
    val judgeRoundState = appState.getJudgeRoundState()


    //parent
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Colors.orange.copy(alpha = 0.1f))
            .padding(Dimensions.Padding.medium)
            .verticalScroll(rememberScrollState())
    ) {


        Card(elevation = Dimensions.Elevation.medium) {
            Column {
                Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                    Text(
                        text = Strings.dayNamesHeader,
                        fontSize = Dimensions.TextSize.large,
                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth().padding(bottom = Dimensions.Padding.medium)
                    )
                    Text(
                        text = Strings.dayFaultsHeader,
                        fontSize = Dimensions.TextSize.large,
                        textAlign = TextAlign.Center,
//                        modifier = Modifier.weight(0.2f).padding(bottom = Dimensions.Padding.medium)
                    )
                }

                //players info column
                Column {
                    judgePlayersState.nickNames.forEachIndexed { playerIndex, name ->
                        //each player info row
                        Row {
                            IQPlayerNameRow(
                                slot = playerIndex, text = name, isInputEnabled = true,
                                colorSlotInactive = Colors.secondary.copy(alpha = 0.7f),
                                colorSlotActive = Colors.primary.copy(alpha = 0.7f),
                                colorName = Colors.orange.copy(alpha = 0.1f),
                                isSlotActive = judgePlayersState.voteNomination[playerIndex],
                                onSlotClick = {

                                    //vote order for judge
                                    viewModel.execute(
                                        JudgeRoundAction.UpdateVoteOrder(
                                            judgeRoundState.voteOrder.toMutableList().apply {
                                                if (judgePlayersState.voteNomination[playerIndex]) {
                                                    removeAll { it == playerIndex+1 }
                                                } else {
                                                    add(playerIndex+1)
                                                }
                                            })
                                    )

                                    //vote indicator
                                    viewModel.execute(
                                        JudgePlayersAction.UpdateVoteNominations(
                                            judgePlayersState.voteNomination
                                                .toMutableList().apply {
                                                    removeAt(playerIndex)
                                                    add(
                                                        playerIndex,
                                                        !judgePlayersState.voteNomination[playerIndex]
                                                    )
                                                })
                                    )

                                }
                            ) { changedText ->
//                        val newNames = slotsState.tourPlayersNames.toMutableList()
//                        newNames[playerIndex] = changedText
//                        viewModel.execute(JudgeSlotsScreenAction.SetTourPlayers(newNames))
                            }
                            TextButton(onClick = {}) {
                                Text(text = dayState.playersFaults[playerIndex].toString())
                            }
                        }

                    }
                }
            }
            //headers row

        }

        Text(text = judgeRoundState.voteOrder.toString())
    }

//    Text(text = "Day\nList of players\nVoting\nFaults\nVote Nomination")
}