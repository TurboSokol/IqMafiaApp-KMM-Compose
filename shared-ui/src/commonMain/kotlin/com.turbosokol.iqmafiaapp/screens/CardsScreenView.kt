package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.turbosokol.iqmafiaapp.components.IQAlertDialogView
import com.turbosokol.iqmafiaapp.components.IQCollapsedSwitchFABView
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.JudgeCardsScreenState
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.slots.JudgeSlotsScreenState
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


//Будет делаться по аналогии со SlotsScreenView
//list игроков будет взят с JudgePlayersState
@Composable
fun CardsScreenView(viewModel: ReduxViewModel) {
    Text(text = "Cards\nCard roles randomizer\nWith choose of card in stack")
    CardsSingleScreenView(viewModel)
}


//SINGLE - в этом контексте - ОДНА (не турнамент) а НЕ одиночная игра.
@Composable
fun CardsSingleScreenView(viewModel: ReduxViewModel) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val appState by stateFlow.collectAsState(Dispatchers.Main)
    val CardsState: JudgeCardsScreenState = appState.getJudgeCardsState() //тут смены нэйминга со Slots на Cards

    Column(modifier = Modifier.fillMaxSize()) {
        if (CardsState.listIndex >= 0) {
            val showingCards = CardsState.cardsList.subList(0, CardsState.listIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Colors.orange.copy(alpha = 0.1f))
                    .padding(start = Dimensions.Padding.medium, end = Dimensions.Padding.medium)
                )
                    {
                        showingCards.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .padding(Dimensions.Padding.xmedium)
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
            }//Конец Row
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
            if (CardsState.cardsList.lastIndex != CardsState.listIndex) {
                viewModel.execute(JudgeSlotsScreenAction.ShowNext)
            } else {
                viewModel.execute(JudgeSlotsScreenAction.SetResetDialogVisible)
            }
        }) {
            Text(
                text = if (CardsState.isHidden) Strings.singleSlotsHiddenLabel else CardsState.cardsList[CardsState.listIndex].toString(),
                fontSize = if (CardsState.isHidden) Dimensions.TextSize.huge else Dimensions.TextSize.xhuge,
                color = Colors.primary
            )
        }

    }//Конец Column


}