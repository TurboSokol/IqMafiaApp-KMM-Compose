package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.theme.Colors
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
fun CardsScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val сardsState: JudgeCardsScreenState = appState.getJudgeCardsState()

    Column(modifier = Modifier.fillMaxSize()) {

        TextButton(modifier = Modifier.fillMaxSize()
            .background(color =
            when(сardsState.cardsList[сardsState.listIndex].type.toString()) {
                "SHERIFF" -> Colors.red
                "DON" -> Colors.darkGrey51
                "RED" -> Colors.red
                "BLACK" -> Colors.darkGrey32
                else -> {Colors.orange.copy(alpha = 0.1f)}
            })
            ,onClick = {
            if (сardsState.cardsList.lastIndex != сardsState.listIndex) {
                viewModel.execute(JudgeCardsScreenAction.ShowNext)
            } else {
                viewModel.execute(JudgeCardsScreenAction.Init)
            }
        }) {
            Text(
                text = if (сardsState.isHidden)
                    Strings.getCard

                else сardsState.cardsList[сardsState.listIndex].type.toString() + " "
                        + сardsState.listIndex.toString(),



                fontSize = if (сardsState.isHidden) Dimensions.TextSize.huge
                else Dimensions.TextSize.huge,

                color = if (!сardsState.isHidden) {
                    when (сardsState.cardsList[сardsState.listIndex].type.toString()) {
                        "SHERIFF" -> Colors.darkGrey32
                        "DON" -> Colors.red
                        "RED" -> Colors.red
                        "BLACK" -> Colors.darkGrey32
                        else -> {
                            Colors.primary
                        }
                    }
                } else {
                    Colors.primary
                }
            )
        }
    }
}