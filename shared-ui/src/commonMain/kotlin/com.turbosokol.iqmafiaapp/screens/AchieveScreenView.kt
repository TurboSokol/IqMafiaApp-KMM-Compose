package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.analytics.players.PlayersState
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun AchieveScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val playersState: MutableState<PlayersState> = remember { mutableStateOf(appState.getPlayersState()) }
    Text(text = "Achievement\nList of players balls and game moves", color = MaterialTheme.colorScheme.onBackground)


    Column {
        playersState.value.allProfilesFromBE.forEach { profile ->
            Text(text = profile.nickName, color = MaterialTheme.colorScheme.onPrimary)
        }
    }



}