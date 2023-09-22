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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.turbosokol.iqmafiaapp.components.IQScoreRow
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardModel
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType
import com.turbosokol.iqmafiaapp.features.app.AppState
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings
import com.turbosokol.iqmafiaapp.viewmodel.ReduxViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
//import org.jetbrains.skia.Color

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun ScoreScreenView(viewModel: ReduxViewModel) {
 val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
 val appState by stateFlow.collectAsState(Dispatchers.Main)
 val playersState = appState.getPlayersState()
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
         text = Strings.scoreCardRate,
         fontSize = Dimensions.TextSize.small,
         textAlign = TextAlign.Center,
         modifier = Modifier
         )

        Text(
         text = Strings.scoreDops,
         fontSize = Dimensions.TextSize.small,
         textAlign = TextAlign.Center,
         modifier = Modifier
        )

       Text(
           text = Strings.comment,
           fontSize = Dimensions.TextSize.small,
           textAlign = TextAlign.Center,
           modifier = Modifier
       )

       }

        playersState.nickNames.forEachIndexed { playerIndex, name ->
          IQScoreRow(
           slot = playerIndex+1,
           colorSlot =
           when (playersState.characterCards[playerIndex].type)  {
               CharacterCardType.RED -> Colors.red
               CharacterCardType.DON -> Color.Gray
               CharacterCardType.SHERIFF -> Color.Yellow
               CharacterCardType.BLACK -> Colors.darkGrey51
               else -> {
                   Color.Cyan} //If you see this color - app goes wrong
           },
           textName = playersState.nickNames[playerIndex],
           rate = 21.92,
           dops = 11.94,
           comment = "A comment",
           modifier = Modifier
          )
       }//End of the second column
    }//End of the card



  }//End of the first outside column


}
}//End of ScoreScreenView composable