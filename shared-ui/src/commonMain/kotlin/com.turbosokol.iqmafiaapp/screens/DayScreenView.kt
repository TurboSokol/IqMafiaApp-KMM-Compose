package com.turbosokol.iqmafiaapp.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    val dayState = appState.getDayState()
    val playersState = appState.getPlayersState()
    val roundState = appState.getRoundState()

    var voteCountDialogVisible by remember { mutableStateOf(false) }
    var voteResultsDialogVisible by remember { mutableStateOf(false) }
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


                playersState.profiles.forEachIndexed { profileIndex, profile ->
//                Box(){
                    IQDayPlayersRow(
                        slot = profileIndex,
                        colorSlot = if (playersState.voteNomination[profileIndex]) MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.75f)
                        else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f),
                        onSlotClick = {
                            //vote order for judge
                            viewModel.execute(
                                RoundAction.UpdateVoteOrder(
                                    roundState.voteCandidates.toMutableList().apply {
                                        if (playersState.voteNomination[profileIndex]) {
                                            removeAll { it == profileIndex + 1 }
                                        } else {
                                            add(profileIndex + 1)
                                        }
                                    }
                                )
                            )

                            //vote indicator
                            viewModel.execute(
                                PlayersAction.UpdateVoteNominations(
                                    playersState.voteNomination.mapIndexed { index, oldNomination ->
                                        if (index == profileIndex) !playersState.value.voteNomination[profileIndex] else oldNomination
                                    }
                                )
                            )
                        },

                        //TODO(REMOVE IF UNUSED)
//                        colorName = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        textName = profile.nickName, isNameInputEnabled = true,
                        onFaultClick = {
                            viewModel.execute(DayScreenAction.UpdateFaults(
                                dayState.playersFaults.mapIndexed { index, oldFault ->
                                    if (index == profileIndex) {
                                        if (oldFault < 4) dayState.playersFaults[profileIndex] + 1 else 0
                                    } else oldFault
                                }
                            ))
                        },
                        colorFault = when (dayState.playersFaults[profileIndex]) {
                            3 -> MaterialTheme.colorScheme.inversePrimary
                            4 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.secondary
                        },
                        textFault = dayState.playersFaults[profileIndex].toString(),
                        allProfilesFromBE = playersState.allProfilesFromBE,
                        profile = playersState.profiles[profileIndex],
                        onProfileChanged = {
                                changedProfile ->
                            viewModel.execute(
                                PlayersAction.UpdateProfiles(playersState.profiles.mapIndexed
                                { newProfileIndex, newProfile ->
                                    if (newProfileIndex == profileIndex) changedProfile else newProfile
                                })
                            )
                        }
                    )
                //TODO(REMANE) //IQDPR end
                }
            }
        } //END card with slots, nicks, faults

            AnimatedVisibility(visible = roundState.voteCandidates.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = Dimensions.Padding.small)
                        .align(CenterHorizontally),
                    text = Strings.voteHintLabel,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            AnimatedVisibility(
                visible = roundState.voteCandidates.isNotEmpty(),
                modifier = Modifier.background(Color.Transparent)
            ) {
                IQDayVoteCard(
                    modifier = Modifier.padding(top = Dimensions.Padding.small),
                    isVisible = roundState.voteCandidates.isNotEmpty(),
                    voteCandidates = roundState.voteCandidates,
                    voteResult = roundState.voteResult,
                    onVoteClick = { voteNominant, voteDialogVisible ->
                        voteNominantSlot.value = voteNominant
                        voteCountDialogVisible = voteDialogVisible
                    }
                )
            }

            //VOTE CARD


            //VOTE COUNT DIALOG
            IQVoteDialogView(
                modifier = Modifier,
                isVisible = voteCountDialogVisible,
                onConfirm = { index: Int ->
                    viewModel.execute(
                        RoundAction.UpdateVoteResults(
                            roundState.voteResult.plus(
                                voteNominantSlot.value to index
                            )
                        )
                    )
                    voteCountDialogVisible = false

                },
                onCancel = { voteCountDialogVisible = false }
            )

            //END DAY DIALOG
            IQEndVoteDialogView(
                modifier = Modifier,
                isVisible = voteResultsDialogVisible,
                onConfirm = { index: List<Int> ->
                    viewModel.execute(RoundAction.RoundCompleted(index))
                    voteResultsDialogVisible = false
                },
                onCancel = { voteResultsDialogVisible = false }
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
                        .clickable { voteResultsDialogVisible = true },
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
        } //END SCROLLABLE
}


