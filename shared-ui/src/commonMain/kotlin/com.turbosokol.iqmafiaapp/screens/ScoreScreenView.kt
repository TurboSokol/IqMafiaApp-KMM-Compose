package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.turbosokol.iqmafiaapp.features.app.AppState
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
fun ScoreScreenView(viewModel: ReduxViewModel) {
 val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
 val appState by stateFlow.collectAsState(Dispatchers.Main)
 //Text(text = "Score\nTable Of Current Game\nTable of current user games", color = Color.Black)

  Column( //The first outside column
   modifier = Modifier.fillMaxSize()
    .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
    .padding(Dimensions.Padding.medium)
    .verticalScroll(rememberScrollState())
  ) {
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
          text = Strings.scoreNumberSymbol,
          fontSize = Dimensions.TextSize.small,
          textAlign = TextAlign.Center,
          modifier = Modifier
         )

         Text(
          text = Strings.scoreNamesHeader,
          fontSize = Dimensions.TextSize.small,
          textAlign = TextAlign.Center,
          modifier = Modifier
         )

         Text(
          text = Strings.scoreCardType,
          fontSize = Dimensions.TextSize.small,
          textAlign = TextAlign.Center,
          modifier = Modifier
         )

        Text(
         text = Strings.scoreCardWin,
         fontSize = Dimensions.TextSize.small,
         textAlign = TextAlign.Center,
         modifier = Modifier
        )

        Text(
         text = Strings.scoreCardRate,
         fontSize = Dimensions.TextSize.small,
         textAlign = TextAlign.Center,
         modifier = Modifier
        )

       }
      }
     }
  }


}