package com.turbosokol.iqmafiaapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQDayPlayersRow
import com.turbosokol.iqmafiaapp.components.IQDayVoteCard
import com.turbosokol.iqmafiaapp.components.dialogs.IQEndVoteDialogView
import com.turbosokol.iqmafiaapp.components.dialogs.IQVoteDialogView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersState
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundAction
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundState
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.day.DayScreenState
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

    val dayState: MutableState<DayScreenState> = remember { mutableStateOf(appState.getDayState()) }
    val playersState: MutableState<PlayersState> = remember { mutableStateOf(appState.getPlayersState()) }
    val roundState: MutableState<RoundState> = remember { mutableStateOf(appState.getRoundState()) }

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


                playersState.value.profiles.forEachIndexed { playerIndex, profile ->
                Box(){
                    IQDayPlayersRow(
                        slot = playerIndex,
                        colorSlot = if (playersState.value.voteNomination[playerIndex]) MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.75f)
                        else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f),
                        onSlotClick = {
                            //vote order for judge
                            viewModel.execute(
                                RoundAction.UpdateVoteOrder(
                                    roundState.value.voteCandidates.toMutableList().apply {
                                        if (playersState.value.voteNomination[playerIndex]) {
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
                                    playersState.value.voteNomination.mapIndexed { index, oldNomination ->
                                        if (index == playerIndex) !playersState.value.voteNomination[playerIndex] else oldNomination
                                    }
                                )
                            )
                        },
//                        colorName = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        textName = profile.nickName, isNameInputEnabled = true,
                        onFaultClick = {
                            viewModel.execute(DayScreenAction.UpdateFaults(
                                dayState.value.playersFaults.mapIndexed { index, oldFault ->
                                    if (index == playerIndex) {
                                        if (oldFault < 4) dayState.value.playersFaults[playerIndex] + 1 else 0
                                    } else oldFault
                                }
                            ))
                        },
                        colorFault = when (dayState.value.playersFaults[playerIndex]) {
                            3 -> MaterialTheme.colorScheme.inversePrimary
                            4 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.secondary
                        },
                        textFault = dayState.value.playersFaults[playerIndex].toString(),
                        allProfilesFromBE = playersState.value.allProfilesFromBE,
                        profile = playersState.value.profiles[playerIndex],
                        onProfileChanged = {
                                changedProfile ->
                            viewModel.execute(
                                PlayersAction.UpdateProfiles(playersState.value.profiles.mapIndexed
                                { newProfileIndex, newProfile ->
                                    if (newProfileIndex == playerIndex) changedProfile else newProfile
                                })
                            )
                        }
                    )//IQDPR end
                }
            }
            } //END card with slots, nicks, faults

            AnimatedVisibility(visible = roundState.value.voteCandidates.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = Dimensions.Padding.small)
                        .align(CenterHorizontally),
                    text = Strings.voteHintLabel,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            AnimatedVisibility(
                visible = roundState.value.voteCandidates.isNotEmpty(),
                modifier = Modifier.background(Color.Transparent)
            ) {
                IQDayVoteCard(
                    modifier = Modifier.padding(top = Dimensions.Padding.small),
                    isVisible = roundState.value.voteCandidates.isNotEmpty(),
                    voteCandidates = roundState.value.voteCandidates,
                    voteResult = roundState.value.voteResult,
                    onVoteClick = { voteNominant, voteDialogVisible ->
                        voteNominantSlot.value = voteNominant
                        voteCountDialogVisible.value = voteDialogVisible
                    }
                )
            }

            //VOTE CARD


            //VOTE COUNT DIALOG
            IQVoteDialogView(
                modifier = Modifier,
                isVisible = voteCountDialogVisible.value,
                onConfirm = { index: Int ->
                    viewModel.execute(
                        RoundAction.UpdateVoteResults(
                            roundState.value.voteResult.plus(
                                voteNominantSlot.value to index
                            )
                        )
                    )
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
                Card(
                    modifier = Modifier.fillMaxHeight()
                        .wrapContentWidth()
                        .background(Color.Transparent)
                        .padding(Dimensions.Padding.medium)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            RoundedCornerShape(Dimensions.CornerRadius.large)
                        )
                        .clickable { voteResultsDialogVisible.value = true },
                    elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(
                        text = Strings.endDayButton,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary)
                            .padding(Dimensions.Padding.smedium)
                    )
                }
            }
            }
        } //END SCROLLABLE
    }
//}

