package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQDayPlayersRow
import com.turbosokol.iqmafiaapp.components.dialogs.IQEndVoteDialogView
import com.turbosokol.iqmafiaapp.components.dialogs.IQVoteDialogView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundAction
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenAction
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DayScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val dayState = appState.getDayState()
    val playersState = appState.getPlayersState()
    val roundState = appState.getRoundState()

    val voteCountDialogVisible = remember { mutableStateOf(false) }
    val voteResultsDialogVisible = remember { mutableStateOf(false) }
    val voteNominantSlot = remember { mutableStateOf(-1) }

    //scrollable parent
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
            .padding(Dimensions.Padding.medium)
            .verticalScroll(rememberScrollState())
    ) {

        //card with slots, names, faults
        Card(elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium)) {

            //players info column
            Column(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
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
                        colorSlot = if (playersState.voteNomination[playerIndex]) MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.75f)
                        else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f),
                        onSlotClick = {
                            //vote order for judge
                            viewModel.execute(
                                RoundAction.UpdateVoteOrder(
                                    roundState.voteCandidates.toMutableList().apply {
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
                                PlayersAction.UpdateVoteNominations(
                                    playersState.voteNomination.mapIndexed { index, oldNomination ->
                                        if (index == playerIndex) !playersState.voteNomination[playerIndex] else oldNomination
                                    }
                                )
                            )
                        },
                        colorName = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        textName = name, isNameInputEnabled = true,
                        onFaultClick = {
                            viewModel.execute(DayScreenAction.UpdateFaults(
                                dayState.playersFaults.mapIndexed { index, oldFault ->
                                    if (index == playerIndex) {
                                        if (oldFault < 4) dayState.playersFaults[playerIndex] + 1 else 0
                                    } else oldFault
                                }
                            ))
                        },
                        colorFault = when (dayState.playersFaults[playerIndex]) {
                            3 -> MaterialTheme.colorScheme.inversePrimary
                            4 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.secondary
                        },
                        textFault = dayState.playersFaults[playerIndex].toString()
                    ) { changedText ->
                        viewModel.execute(
                            PlayersAction.UpdateNickNames(
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
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline)), elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium)
        ) {
            Column(modifier = Modifier.background(Color.Transparent)) {
                //NOMINATED PLAYERS
                Row(
                    modifier = Modifier.background(Color.Transparent),
                    horizontalArrangement = Arrangement.Start
                ) {
                    roundState.voteCandidates.forEach { voteNominant ->
                        var votingState by remember { mutableStateOf(VotingState.INIT) }

                        Card(modifier = if (roundState.voteCandidates.size < 6) Modifier else Modifier.weight(1f)) {
                            TextButton(modifier = Modifier.border(
                                BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.outline
                                )
                            ).background(color = when(votingState) {
                                VotingState.INIT -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                                VotingState.VOTE_IN_PROGRESS -> MaterialTheme.colorScheme.tertiary
                                VotingState.VOTE_FINISHED -> MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.4f)
                            }),
                                onClick = {
                                    MainScope().launch {
                                        votingState = VotingState.VOTE_IN_PROGRESS
                                        delay(1500)
                                        votingState = VotingState.VOTE_FINISHED
                                        voteNominantSlot.value = voteNominant
                                        voteCountDialogVisible.value = true
                                    }
                                }) {
                                Text(text = voteNominant.toString(), color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.ExtraBold)
                            }
                        }

                    }
                }

                //VOTE COUNT
                Row(
                    modifier = Modifier.background(Color.Transparent),
                    horizontalArrangement = Arrangement.Start
                ) {
                roundState.voteCandidates.forEach { voteNomination ->
                    Card(modifier = if (roundState.voteCandidates.size < 6) Modifier else Modifier.weight(1f)) {
                        TextButton(modifier = Modifier.border(
                            BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.outline
                            )
                        ).background(Color.Transparent),
                            onClick = { /* no-op */ }) {
                            val countVoting = roundState.voteResult[voteNomination].toString()
                            Text(text = if (countVoting == "null") "-" else countVoting, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
            }
            }
        } //END VOTE CARD

        //VOTE COUNT DIALOG
        IQVoteDialogView(
            modifier = Modifier,
            isVisible = voteCountDialogVisible.value,
            onConfirm = { index: Int ->
                viewModel.execute(RoundAction.UpdateVoteResults(roundState.voteResult.plus(voteNominantSlot.value to index)))
                voteCountDialogVisible.value = false

            },
            onCancel = { voteCountDialogVisible.value = false }
        )

        //END DAY DIALOG
        IQEndVoteDialogView(
            modifier = Modifier,
            isVisible = voteResultsDialogVisible.value,
            onConfirm = { index: List<Int> ->
                viewModel.execute(RoundAction.RoundCompleted(index))
                voteResultsDialogVisible.value = false
            },
            onCancel = { voteResultsDialogVisible.value = false }
        )


        Row(
            modifier = Modifier.fillMaxWidth().padding(top = Dimensions.Padding.medium),
            horizontalArrangement = Arrangement.Center
        ) {
             Card(modifier = Modifier.fillMaxHeight()
                .wrapContentWidth()
                .background(Color.Transparent)
                .padding(Dimensions.Padding.medium)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(Dimensions.CornerRadius.large))
                .clickable { voteResultsDialogVisible.value = true },
                elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = Strings.endDayButton,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary).padding(Dimensions.Padding.smedium)
                )
            }
        }
    } //END SCROLLABLE
}


    enum class VotingState {
        INIT, VOTE_IN_PROGRESS, VOTE_FINISHED
    }