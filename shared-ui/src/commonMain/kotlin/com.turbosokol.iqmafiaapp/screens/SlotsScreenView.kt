package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQAlertDialogView
import com.turbosokol.iqmafiaapp.components.IQCollapsedSwitchFABView
import com.turbosokol.iqmafiaapp.components.IQDialog
import com.turbosokol.iqmafiaapp.components.IQLoaderView
import com.turbosokol.iqmafiaapp.components.IQPlayerNameRow
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import com.turbosokol.iqmafiaapp.util.tournamentShuffleSlots
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Stable
@Composable
fun SlotsScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState: JudgeSlotsScreenState = appState.getJudgeSlotsState()

    Box(modifier = Modifier.fillMaxSize()) {
        IQAlertDialogView(
            modifier = Modifier.align(Alignment.Center)
                .matchParentSize(),
            isVisible = slotsState.isResetDialogVisible,
            label = "Are you sure you want to reset?",
            onConfirm = {
                viewModel.execute(JudgeSlotsScreenAction.SetResetDialogVisible)
                viewModel.execute(JudgeSlotsScreenAction.Init(slotsState.isTourMode))
            },
            onCancel = { viewModel.execute(JudgeSlotsScreenAction.SetResetDialogVisible) }
        )

        if (slotsState.isTourMode) {
            SlotsTourView(viewModel)
        } else {
            SlotsSingleGameView(viewModel)
        }

        IQCollapsedSwitchFABView(
            modifier = Modifier.align(Alignment.BottomEnd).padding(Dimensions.Padding.small),
            collapsedText = Strings.slotsSwitchModeButtonSingeLabel,
            activeCollapsedText = Strings.slotsSwitchModeButtonTourLabel,
            expandedText = Strings.slotsSwitchModeButtonLabel,
            isToogled = slotsState.isTourMode,
            onToogleClick = { viewModel.execute(JudgeSlotsScreenAction.SetIsTourMode) }
        )
    }
}

@Composable
fun SlotsSingleGameView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState = appState.getJudgeSlotsState()


    Column(modifier = Modifier.fillMaxSize()) {

        if (slotsState.listIndex >= 0) {
            val showingNumbers = slotsState.slotsList.subList(0, slotsState.listIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Colors.orange.copy(alpha = 0.1f))
                    .padding(start = Dimensions.Padding.medium, end = Dimensions.Padding.medium),
                horizontalArrangement = if (showingNumbers.count() > 8) Arrangement.SpaceEvenly else Arrangement.Start
            ) {
                showingNumbers.forEach { item ->
                    Box(
                        modifier = Modifier
                            .padding(Dimensions.Padding.smedium)
                            .background(color = Colors.imageBackground)
                    ) {
                        Text(
                            text = item.toString(),
                            fontSize = Dimensions.TextSize.large,
                            color = Colors.primary,
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


        TextButton(modifier = Modifier.fillMaxSize()
            .background(color = Colors.orange.copy(alpha = 0.1f))
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    viewModel.execute(
                        JudgeSlotsScreenAction.SetResetDialogVisible
                    )
                })
            }, onClick = {
            if (slotsState.slotsList.lastIndex != slotsState.listIndex) {
                viewModel.execute(JudgeSlotsScreenAction.ShowNext)
            } else {
                viewModel.execute(JudgeSlotsScreenAction.SetResetDialogVisible)
            }
        }) {
            Text(
                text = if (slotsState.isHidden) Strings.singleSlotsHiddenLabel else slotsState.slotsList[slotsState.listIndex].toString(),
                fontSize = if (slotsState.isHidden) Dimensions.TextSize.huge else Dimensions.TextSize.xhuge,
                color = Colors.primary
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SlotsTourView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState = appState.getJudgeSlotsState()
    val keyboard = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .background(Colors.skyBlue.copy(alpha = 0.1f))
            .padding(Dimensions.Padding.medium)
    ) {
        Text(
            text = Strings.tourSlotsNamesHeader,
            fontSize = Dimensions.TextSize.large,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = Dimensions.Padding.medium)
        )

        Card(elevation = Dimensions.Elevation.medium) {
            Column {
                slotsState.tourPlayersNames.forEachIndexed { index, name ->
                    IQPlayerNameRow(modifier = Modifier,
                        slot = index, textName = name, isInputEnabled = true,
                        colorSlot = Colors.secondary.copy(alpha = 0.7f),
                        colorName = Colors.orange.copy(alpha = 0.1f)
                    ) { changedText ->
                        val newNames = slotsState.tourPlayersNames.toMutableList()
                        newNames[index] = changedText
                        viewModel.execute(JudgeSlotsScreenAction.SetTourPlayers(newNames))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        var gamesCount by remember { mutableStateOf(slotsState.tourGamesCount.toString()) }

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimensions.Padding.medium, bottom = 70.dp)
        ) {
            OutlinedTextField(modifier = Modifier.weight(0.8f),
                value = gamesCount,
                onValueChange = { changedText ->
                    gamesCount = changedText
                    if (changedText.isNotBlank()) viewModel.execute(
                        JudgeSlotsScreenAction.SetTourGamesCount(
                            gamesCount.toInt()
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text(text = Strings.tourSlotsGamesCountLabel) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Colors.primary,
                    unfocusedBorderColor = Colors.darkGrey32
                ),
                trailingIcon = {
                    TextButton(modifier = Modifier, onClick = {
                        keyboard?.hide()

                        if (slotsState.tourGamesCount <= 100) {
                            viewModel.execute(JudgeSlotsScreenAction.SetTourSlotsList(emptyList()))
                            CoroutineScope(Dispatchers.Main + Job()).launch {

                                val shuffled = async { tournamentShuffleSlots(
                                    slotsState.tourPlayersNames,
                                    slotsState.tourGamesCount
                                )}

                                viewModel.execute(JudgeSlotsScreenAction.SetTourSlotsList(shuffled.await()))
                            }

                            }

                        else {
                            gamesCount = "100"
                            JudgeSlotsScreenAction.SetTourGamesCount(100)
                        }

                    }) {
                        Text(text = Strings.tourSlotsGenerateButton, color = Colors.secondary)
                    }
                })
        }

        if (slotsState.inProgress) {
            IQDialog(dismiss = { /* no-op */ }) {
                IQLoaderView(
                    modifier = Modifier.padding(100.dp),
                    strokeColor = Colors.secondary,
                    strokeSize = 7.dp
                ) { /* no-op */ }
            }
        }

        if (slotsState.tourSlotsList.isNotEmpty()) {
            Text(
                text = Strings.tourSlotsGamesListHeader,
                fontSize = Dimensions.TextSize.large,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = Dimensions.Padding.medium)
            )

            Column {
                slotsState.tourSlotsList.forEachIndexed { index, gameSlotsList ->
                    Card(
                        modifier = Modifier.padding(top = Dimensions.Padding.medium)
                            .background(color = Colors.white)
                            .border(width = 1.dp, color = Color.LightGray),
                        elevation = Dimensions.Elevation.medium
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxSize()
                                    .background(color = Color.Transparent),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = Dimensions.Padding.xsmall),
                                    text = "Game ${index + 1}",
                                    fontSize = Dimensions.TextSize.medium
                                )
                            }

                            gameSlotsList.forEachIndexed { index, name ->
                                IQPlayerNameRow(
                                    modifier = Modifier,
                                    slot = index,
                                    textName = name,
                                    isInputEnabled = false
                                ) { changedText ->
                                    val newNames = slotsState.tourPlayersNames.toMutableList()
                                    newNames.removeAt(index)
                                    newNames.add(index, changedText)
                                    viewModel.execute(JudgeSlotsScreenAction.SetTourPlayers(newNames))
                                }
                            }
                        }
                    }
                }

                Card(modifier = Modifier.align(Alignment.CenterHorizontally).padding(Dimensions.Padding.medium), elevation = Dimensions.Elevation.small, border = BorderStroke(1.dp, Colors.gray), shape = Shapes().medium) {
                    TextButton( onClick = {
                        viewModel.execute(
                            JudgeSlotsScreenAction.SetResetDialogVisible
                        )
                    },
                        shape = Shapes().medium
                    ) {
                        Text(modifier = Modifier, text = Strings.resetDialogLabel, color = Colors.secondary, fontSize = Dimensions.TextSize.smedium)
                    }
                }

            }
        }


    }
}