package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.CollapsedSwitchFAB
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

    Box(
        modifier = Modifier.background(color = Colors.secondaryLighter.copy(alpha = 0.3f))
            .fillMaxSize()
    ) {
        var isTourMode = mutableStateOf(false)

        if (slotsState.isTourMode) {
            SlotsTourView(slotsState)
        } else {
            SlotsSingleGameView(viewModel)
        }

        CollapsedSwitchFAB(
            modifier = Modifier.align(Alignment.BottomEnd).padding(Dimensions.Padding.small),
            collapsedText = "Game",
            activeCollapsedText = "Tour",
            expandedText = "Tournament Mode",
            isToogled = isTourMode.value,
            onToogleClick = { isTourMode.value = !isTourMode.value }
        )

//        Text(text = "Slots\nRandomizer 10 numbers for players position\nRandomizer for tournamets (Multiple games with names)")
    }
}

@Composable
fun SlotsTourView(slotsScreenState: JudgeSlotsScreenState) {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
fun SlotsSingleGameView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val slotsScreenState = appState.getJudgeSlotsState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .padding(Dimensions.Padding.middle)
                .background(color = Colors.imageBackground)
        ) {
            items(slotsScreenState.listIndex + 1) {
                Box(
                    modifier = Modifier
                        .padding(Dimensions.Padding.small)
                        .background(color = Colors.imageBackground)
                ) { Text(text = slotsScreenState.slotsList[slotsScreenState.listIndex].toString()) }
            }
        }

        Button(modifier = Modifier.fillMaxSize(), onClick = {
            if (slotsScreenState.slotsList.lastIndex != slotsScreenState.listIndex) viewModel.execute(JudgeSlotsScreenAction.ShowNext)
        }) {
            Text(text = if (slotsScreenState.isHidden) "Click Me" else slotsScreenState.slotsList[slotsScreenState.listIndex].toString())
        }
    }
}

