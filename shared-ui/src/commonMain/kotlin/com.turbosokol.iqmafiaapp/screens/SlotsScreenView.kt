package com.turbosokol.iqmafiaapp.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.dialogs.IQAlertDialogView
import com.turbosokol.iqmafiaapp.components.IQCollapsedSwitchFABView
import com.turbosokol.iqmafiaapp.components.dialogs.IQDialog
import com.turbosokol.iqmafiaapp.components.IQLoaderView
import com.turbosokol.iqmafiaapp.components.IQPlayerNameRow
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.SlotsScreenState
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
    val slotsState: SlotsScreenState = appState.getSlotsState()

    Box(modifier = Modifier.fillMaxSize()) {
        IQAlertDialogView(
            modifier = Modifier.align(Alignment.Center)
                .matchParentSize(),
            isVisible = slotsState.isResetDialogVisible,
            label = "Are you sure you want to reset?",
            onConfirm = {
                viewModel.execute(SlotsScreenAction.SetResetDialogVisible)
                viewModel.execute(SlotsScreenAction.Init(slotsState.isTourMode))
            },
            onCancel = { viewModel.execute(SlotsScreenAction.SetResetDialogVisible) }
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
            isToggled = slotsState.isTourMode,
            onToggleClick = { viewModel.execute(SlotsScreenAction.SetIsTourMode) }
        )
    }
}

@Composable
fun SlotsSingleGameView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState = appState.getSlotsState()


    Column(modifier = Modifier.fillMaxSize()) {

        if (slotsState.listIndex >= 0) {
            val showingNumbers = slotsState.slotsList.subList(0, slotsState.listIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))  //Used slots top line
                    .padding(start = Dimensions.Padding.medium, end = Dimensions.Padding.medium),
                horizontalArrangement = if (showingNumbers.count() > 8) Arrangement.SpaceEvenly else Arrangement.Start
            ) {
                showingNumbers.forEach { item ->
                    Box(
                        modifier = Modifier
                            .padding(Dimensions.Padding.smedium)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)//surface.copy(alpha = 0.1f))
                    ) {
                        Text(
                            text = item.toString(),
                            fontSize = Dimensions.TextSize.large,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


        TextButton(modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
            , onClick = {
            if (slotsState.slotsList.lastIndex != slotsState.listIndex) {
                viewModel.execute(SlotsScreenAction.ShowNext)
            } else {
                viewModel.execute(SlotsScreenAction.SetResetDialogVisible)
            }
        }) {
            Text(
                text = if (slotsState.isHidden) Strings.singleSlotsHiddenLabel else slotsState.slotsList[slotsState.listIndex].toString(),
                fontSize = if (slotsState.isHidden) Dimensions.TextSize.huge else Dimensions.TextSize.xhuge,
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SlotsTourView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState = appState.getSlotsState()
    val keyboard = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f))
            .padding(Dimensions.Padding.medium)
    ) {
        Text(
            text = Strings.tourSlotsNamesHeader,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = Dimensions.TextSize.large,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = Dimensions.Padding.medium)
        )

        Card(elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium)) {
            Column {
                slotsState.tourPlayersNames.forEachIndexed { index, name ->
                    IQPlayerNameRow(modifier = Modifier,
                        slot = index, textName = name, isInputEnabled = true,
                        colorSlot = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                        colorName = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                    ) { changedText ->
                        val newNames = slotsState.tourPlayersNames.toMutableList()
                        newNames[index] = changedText
                        viewModel.execute(SlotsScreenAction.SetTourPlayers(newNames))
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
                        SlotsScreenAction.SetTourGamesCount(
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                ),
                trailingIcon = {
                    TextButton(modifier = Modifier, onClick = {
                        keyboard?.hide()

                        if (slotsState.tourGamesCount <= 100) {
                            viewModel.execute(SlotsScreenAction.SetTourSlotsList(emptyList()))
                            CoroutineScope(Dispatchers.Default + Job()).launch {

                                val shuffled = async { tournamentShuffleSlots(
                                    slotsState.tourPlayersNames,
                                    slotsState.tourGamesCount
                                )}

                                viewModel.execute(SlotsScreenAction.SetTourSlotsList(shuffled.await()))
                            }

                            }

                        else {
                            gamesCount = "100"
                            SlotsScreenAction.SetTourGamesCount(100)
                        }

                    }) {
                        Text(text = Strings.tourSlotsGenerateButton, color = MaterialTheme.colorScheme.tertiary)
                    }
                })
        }

        if (slotsState.inProgress) {
            IQDialog(dismiss = { /* no-op */ }) {
                IQLoaderView(
                    modifier = Modifier.padding(100.dp),
                    strokeColor = MaterialTheme.colorScheme.tertiary,
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
                            .background(color = MaterialTheme.colorScheme.onBackground)
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer),
                        elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium)
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
                                    viewModel.execute(SlotsScreenAction.SetTourPlayers(newNames))
                                }
                            }
                        }
                    }
                }

                Card(modifier = Modifier.align(Alignment.CenterHorizontally).padding(Dimensions.Padding.medium), elevation = CardDefaults.cardElevation(Dimensions.Elevation.small), border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline), shape = Shapes().medium) {
                    TextButton( onClick = {
                        viewModel.execute(
                            SlotsScreenAction.SetResetDialogVisible
                        )
                    },
                        shape = Shapes().medium
                    ) {
                        Text(modifier = Modifier, text = Strings.resetDialogLabel, color = MaterialTheme.colorScheme.tertiary, fontSize = Dimensions.TextSize.smedium)
                    }
                }
            }
        }


    }
}