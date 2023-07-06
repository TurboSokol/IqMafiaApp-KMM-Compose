package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.turbosokol.iqmafiaapp.components.IQPlayerNameRow
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction
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
fun DayScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val dayState = appState.getJudgeDayState()
    val judgePlayersState = appState.getJudgePlayersState()
    val judgeRoundState = appState.getJudgeRoundState()


    //parent
    Column(modifier = Modifier.fillMaxSize().background(color = Colors.orange.copy(alpha = 0.1f))) {
//        Text(
//            text = "SET PLAYERS",
//            fontSize = Dimensions.TextSize.large,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth().padding(bottom = Dimensions.Padding.medium)
//        )
//
//        Card(elevation = Dimensions.Elevation.medium) {
//            Column {
//                judgePlayersState.nickNames.forEachIndexed { index, name ->
//                    IQPlayerNameRow(
//                        index = index, text = name, isInputEnabled = true,
//                        colorIndex = Colors.secondary.copy(alpha = 0.7f),
//                        colorName = Colors.orange.copy(alpha = 0.1f)
//                    ) { changedText ->
//                        val newNames = slotsState.tourPlayersNames.toMutableList()
//                        newNames[index] = changedText
//                        viewModel.execute(JudgeSlotsScreenAction.SetTourPlayers(newNames))
//                    }
//                }
//            }
//        }
    }

    Text(text = "Day\nList of players\nVoting\nFaults\nVote Nomination")
}