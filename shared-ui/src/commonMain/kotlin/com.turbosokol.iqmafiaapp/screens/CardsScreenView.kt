package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.turbosokol.iqmafiaapp.components.dialogs.IQAlertDialogView
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenAction
import com.turbosokol.iqmafiaapp.features.judge.screens.cards.CardsScreenState
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
    val cardsState: CardsScreenState = appState.getCardsState()

    Box(modifier = Modifier.fillMaxSize()) {
        IQAlertDialogView(
            modifier = Modifier.align(Alignment.Center).matchParentSize(),
            isVisible = cardsState.isResetDialogVisible,
            label = "Are you sure you want to reset?",
            onConfirm = {
                viewModel.execute(CardsScreenAction.SetResetDialogVisible)
                viewModel.execute(CardsScreenAction.Init)
                        },
            onCancel = { viewModel.execute(CardsScreenAction.SetResetDialogVisible) }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextButton(modifier = Modifier.fillMaxSize()
            .background(color =
            if (cardsState.isHidden) {
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
            } else {
                when(cardsState.cardsList[cardsState.listIndex].type) {
                    CharacterCardType.SHERIFF -> Colors.red
                    CharacterCardType.DON -> Colors.darkGrey51
                    CharacterCardType.RED -> Colors.red
                    CharacterCardType.BLACK -> Colors.darkGrey51
                }
            })
            ,onClick = {
                if (cardsState.listIndex == cardsState.cardsList.lastIndex && cardsState.isHidden) {
                    viewModel.execute(CardsScreenAction.SetResetDialogVisible)
                } else {
                    viewModel.execute(CardsScreenAction.ShowNext)
            }
        }) {
            Text(
                text = if (cardsState.isHidden) {
                    Strings.getCard
                } else {
                    when (cardsState.cardsList[cardsState.listIndex].type) {
                        CharacterCardType.DON -> "D"
                        CharacterCardType.SHERIFF -> "S"
                        else -> ""
                    }
                },
                fontSize = if (cardsState.isHidden) Dimensions.TextSize.huge
                else Dimensions.TextSize.xhuge,
                fontWeight = FontWeight.Bold,
                color = if (!cardsState.isHidden) {
                    when (cardsState.cardsList[cardsState.listIndex].type) {
                        CharacterCardType.SHERIFF -> Colors.darkGrey51
                        CharacterCardType.DON -> Colors.red
                        else -> Color.Transparent
                    }
                } else {
                    MaterialTheme.colorScheme.inversePrimary
                }
            )
        }
    }
}