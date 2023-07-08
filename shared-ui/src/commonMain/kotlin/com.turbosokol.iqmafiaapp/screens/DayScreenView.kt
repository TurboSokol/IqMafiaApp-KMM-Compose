package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.turbosokol.iqmafiaapp.components.IQDayPlayersRow
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersAction
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundAction
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenAction
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
    val playersState = appState.getJudgePlayersState()
    val roundState = appState.getJudgeRoundState()


    //scrollable parent
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Colors.orange.copy(alpha = 0.1f))
            .padding(Dimensions.Padding.medium)
            .verticalScroll(rememberScrollState())
    ) {

        //card with slots, names, faults
        Card(elevation = Dimensions.Elevation.medium) {

                //players info column
                Column(modifier = Modifier.background(color = Colors.orange.copy(alpha = 0.1f)).padding(Dimensions.Padding.xsmall)) {

                    //headers
                    Row(modifier = Modifier.fillMaxWidth().padding(start = Dimensions.Padding.medium, end = Dimensions.Padding.small), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = Strings.daySlotsHeader,
                            fontSize = Dimensions.TextSize.small,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )

                        Text(
                            text = Strings.dayNamesHeader,
                            fontSize = Dimensions.TextSize.small,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )

                        Text(
                            text = Strings.dayFaultsHeader,
                            fontSize = Dimensions.TextSize.small,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    }


                    playersState.nickNames.forEachIndexed { playerIndex, name ->

                        IQDayPlayersRow(
                            slot = playerIndex,
                            colorSlot = if(playersState.voteNomination[playerIndex]) Colors.primary.copy(alpha = 0.75f) else Colors.secondary.copy(alpha = 0.75f),
                            onSlotClick = {
                                //vote order for judge
                                viewModel.execute(
                                    JudgeRoundAction.UpdateVoteOrder(
                                        roundState.voteOrder.toMutableList().apply {
                                            if (playersState.voteNomination[playerIndex]) {
                                                removeAll { it == playerIndex + 1 }
                                            } else {
                                                add(playerIndex + 1)
                                            }
                                        }
                                    )
                                )

                                //vote indicator
                                viewModel.execute(
                                    JudgePlayersAction.UpdateVoteNominations(
                                        playersState.voteNomination.mapIndexed { index, oldNomination ->
                                            if (index == playerIndex) !playersState.voteNomination[playerIndex] else oldNomination
                                        }
                                    )
                                )
                            },
                            colorName = Colors.orange.copy(alpha = 0.1f),
                            textName = name, isNameInputEnabled = true,
                            onFaultClick = { viewModel.execute(JudgeDayScreenAction.UpdateFaults(
                                dayState.playersFaults.mapIndexed { index, oldFault ->
                                    if (index == playerIndex) {
                                        if (oldFault < 4) dayState.playersFaults[playerIndex] + 1 else 0
                                    } else oldFault
                                }
                            )) },
                            colorFault = when(dayState.playersFaults[playerIndex]) {
                                3 -> Colors.secondary
                                4 -> Colors.gray
                                else -> Colors.orange },
                            textFault = dayState.playersFaults[playerIndex].toString()
                        ) { changedText ->
                            viewModel.execute(
                                JudgePlayersAction.UpdateNickNames(
                                    playersState.nickNames.mapIndexed { index, nick ->
                                        if (index == playerIndex) changedText else nick
                                    })
                            )
                        }




                    }

//    Text(text = "Day\nList of players\nVoting\nFaults\nVote Nomination")

                }
        } //END card with slots, nicks, faults

        Text(text = roundState.voteOrder.toString())


    }
}