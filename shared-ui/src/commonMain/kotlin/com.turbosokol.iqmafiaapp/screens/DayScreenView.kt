package com.turbosokol.iqmafiaapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQDayPlayersRow
import com.turbosokol.iqmafiaapp.components.IQDialog
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.players.JudgePlayersAction
import com.turbosokol.iqmafiaapp.features.judge.round.JudgeRoundAction
import com.turbosokol.iqmafiaapp.features.judge.screens.day.JudgeDayScreenAction
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DayScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val dayState = appState.getJudgeDayState()
    val playersState = appState.getJudgePlayersState()
    val roundState = appState.getJudgeRoundState()

    var voteCountDialogVisible by remember { mutableStateOf(false) }
    val voteNominantSlot = remember { mutableStateOf(-1) }


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
            Column(
                modifier = Modifier.background(color = Colors.orange.copy(alpha = 0.1f))
                    .padding(Dimensions.Padding.xsmall)
            ) {

                //headers
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = Dimensions.Padding.medium, end = Dimensions.Padding.small),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
                        colorSlot = if (playersState.voteNomination[playerIndex]) Colors.primary.copy(
                            alpha = 0.75f
                        ) else Colors.secondary.copy(alpha = 0.75f),
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
                        onFaultClick = {
                            viewModel.execute(JudgeDayScreenAction.UpdateFaults(
                                dayState.playersFaults.mapIndexed { index, oldFault ->
                                    if (index == playerIndex) {
                                        if (oldFault < 4) dayState.playersFaults[playerIndex] + 1 else 0
                                    } else oldFault
                                }
                            ))
                        },
                        colorFault = when (dayState.playersFaults[playerIndex]) {
                            3 -> Colors.secondary
                            4 -> Colors.gray
                            else -> Colors.orange
                        },
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
            }
        } //END card with slots, nicks, faults

        //VOTE CARD
        Card(
            modifier = Modifier.padding(top = Dimensions.Padding.medium)
                .border(BorderStroke(1.dp, Colors.gray)), elevation = Dimensions.Elevation.medium
        ) {
            Column(modifier = Modifier.background(Color.Transparent)) {
                //NOMINATED PLAYERS
                Row(
                    modifier = Modifier.background(Color.Transparent),
                    horizontalArrangement = Arrangement.Start
                ) {
                    roundState.voteOrder.forEach { voteNominant ->
                        var voteButtonColor by remember { mutableStateOf(Colors.orange.copy(alpha = 0.6f)) }
                        Card(modifier = if (roundState.voteOrder.size < 6) Modifier else Modifier.weight(1f)) {
                            TextButton(modifier = Modifier.border(
                                BorderStroke(
                                    1.dp,
                                    Colors.gray
                                )
                            ).background(voteButtonColor),
                                onClick = {
                                    MainScope().launch {
                                        voteButtonColor = Colors.secondary
                                        delay(1500)
                                        voteButtonColor = Colors.primary.copy(alpha = 0.4f)
                                        voteNominantSlot.value = voteNominant
                                        voteCountDialogVisible = true
                                    }
                                }) {
                                Text(text = voteNominant.toString(), color = Colors.darkGrey51, fontWeight = FontWeight.ExtraBold)
                            }
                        }

                    }
                }

                //VOTE COUNT
                Row(modifier = Modifier.background(Color.Transparent),
                horizontalArrangement = Arrangement.Start
                ) {
                roundState.voteOrder.forEach{ voteNomination ->
                    Card(modifier = if (roundState.voteOrder.size < 6) Modifier else Modifier.weight(1f)) {
                        TextButton(modifier = Modifier.border(
                            BorderStroke(
                                1.dp,
                                Colors.gray
                            )
                        ).background(Color.Transparent),
                            onClick = {/* no-op */ }) {
                            val countVoting = roundState.voteResult.get(voteNomination).toString()
                            Text(text = if (countVoting == "null") "-" else countVoting, color = Colors.darkGrey51, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
            }
            }
        } //END VOTE CARD

        //VOTE COUNT DIALOG
        AnimatedVisibility(visible = voteCountDialogVisible) {
            IQDialog(dismiss = {
                voteCountDialogVisible = false
            }) {
                Surface(
                    shape = RoundedCornerShape(Dimensions.CornerRadius.xlarge),
                    elevation = Dimensions.Elevation.xlarge,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(Dimensions.Padding.large),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(Dimensions.Padding.medium)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = Dimensions.Padding.medium),
                            text = Strings.voteCountDialogLabel,
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 1)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "1",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 2)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "2",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 3)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "3",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 4)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "4",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 5)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "5",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 6)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "6",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 7)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "7",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 8)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "8",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 9)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "9",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            TextButton(onClick = {
                                viewModel.execute(JudgeRoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to 0)))
                                voteCountDialogVisible = false
                            }) {
                                Text(
                                    text = "0",
                                    color = Colors.secondary,
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = Dimensions.Padding.medium),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(modifier = Modifier.border(BorderStroke(1.dp, Colors.gray)), onClick = {
                viewModel.execute(JudgeRoundAction.RoundCompleted)
            }) {
                Text("End Vote")
            }
        }
    } //END SCROLLABLE
}