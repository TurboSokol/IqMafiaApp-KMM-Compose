package com.turbosokol.iqmafiaapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.IQAlertDialogView
import com.turbosokol.iqmafiaapp.components.IQCollapsedSwitchFABView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

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
                viewModel.execute(JudgeSlotsScreenAction.Init)
                viewModel.execute(JudgeSlotsScreenAction.SetResetDialogVisible)
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
            collapsedText = "Game",
            activeCollapsedText = "Tour",
            expandedText = "Tournament Mode",
            isToogled = slotsState.isTourMode,
            onToogleClick = { viewModel.execute(JudgeSlotsScreenAction.SetIsTourMode) }
        )

//        Text(text = "Slots\nRandomizer 10 numbers for players position\nRandomizer for tournamets (Multiple games with names)")
    }
}

@Composable
fun SlotsTourView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsState = appState.getJudgeSlotsState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        slotsState.tourPlayersNames.forEachIndexed { index, name ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = (index + 1).toString(), modifier = Modifier.weight(0.2f))
                TextField(
                    value = name,
                    modifier = Modifier.weight(0.8f),
                    onValueChange = { changedValue: String ->
                        val newNames = slotsState.tourPlayersNames.toMutableList()
                        newNames.removeAt(index)
                        newNames.add(index, changedValue)
                        viewModel.execute(JudgeSlotsScreenAction.SetTourPlayers(newNames))
                    })
            }
        }

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
            ) {
                showingNumbers.forEach { item ->
                    Box(
                        modifier = Modifier
                            .padding(Dimensions.Padding.small)
                            .background(color = Colors.imageBackground)
                    ) {
                        Text(
                            text = item.toString(),
                            fontSize = Dimensions.TextSize.medium,
                            color = Colors.primary
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
                text = if (slotsState.isHidden) "Get Slot" else slotsState.slotsList[slotsState.listIndex].toString(),
                fontSize = Dimensions.TextSize.huge,
                color = Colors.primary
            )
        }
    }
}