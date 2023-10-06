package com.turbosokol.iqmafiaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    Card(elevation = CardDefaults.cardElevation(Dimensions.Elevation.medium), modifier = Modifier.fillMaxWidth(1f)) {
     //players info column
       //headers
       Row(
        modifier = Modifier//.fillMaxWidth().weight(1f)
         .padding(/*start = Dimensions.Padding.medium,*/ end = Dimensions.Padding.small),
//        horizontalArrangement = Arrangement.SpaceBetween

       ) {
           Column(modifier = Modifier.weight(0.1f)) {
               Text(
                   text = Strings.scoreNumberSymbol,
                   fontSize = Dimensions.TextSize.small,
                   modifier = Modifier.fillMaxWidth()
               )
               playersState.nickNames.forEachIndexed { playerIndex, name ->
                   Text((playerIndex + 1).toString(), modifier = Modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).fillMaxWidth())
               }
           }

           Column(modifier = Modifier.weight(0.3f)) {
               Text(
                   text = Strings.scoreNamesHeader,
                   fontSize = Dimensions.TextSize.small,
                   modifier = Modifier.fillMaxWidth()
               )
               playersState.nickNames.forEachIndexed { playerIndex, name ->
                   Text(name, modifier = Modifier.background(color =
               when (playersState.characterCards[playerIndex].type)  {
               CharacterCardType.RED -> Colors.red
               CharacterCardType.DON -> Color.LightGray
               CharacterCardType.SHERIFF -> Color.Yellow
               CharacterCardType.BLACK -> Colors.darkGrey51
               else -> {
               Color.Cyan} //If you see this color - app goes wrong
               }).border(0.5.dp, MaterialTheme.colorScheme.outline).fillMaxWidth()
                       ,color =
                       when (playersState.characterCards[playerIndex].type)  {
                           CharacterCardType.RED -> Color.White
                           CharacterCardType.DON -> Color.Black
                           CharacterCardType.SHERIFF -> Color.Black
                           CharacterCardType.BLACK -> Color.White
                           else -> {
                               Color.Cyan} //If you see this color - app goes wrong
                       }

                   )
           }}

           Column(modifier = Modifier.weight(0.15f)) {
               Text(
                   text = Strings.scoreCardRate,
                   fontSize = Dimensions.TextSize.small,
                   modifier = Modifier.fillMaxWidth()
               )
               playersState.nickNames.forEachIndexed { playerIndex, name ->
                   Text(21.92.toString(), modifier = Modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).fillMaxWidth())
               }
           }

           Column(modifier = Modifier.weight(0.15f)) {
               Text(
                   text = Strings.scoreDops,
                   fontSize = Dimensions.TextSize.small,
                   modifier = Modifier.fillMaxWidth()
               )
               playersState.nickNames.forEachIndexed { playerIndex, name ->
                   Text(11.94.toString(), modifier = Modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).fillMaxWidth())
               }
           }

           Column(modifier = Modifier.weight(0.3f)) {
               Text(
                   text = Strings.comment,
                   fontSize = Dimensions.TextSize.small,
                   modifier = Modifier.fillMaxWidth()

               )
               playersState.nickNames.forEachIndexed { playerIndex, name ->
                   TextField("A comment", onValueChange = {}, modifier = Modifier.fillMaxWidth())
               }
           }


       }

//        playersState.nickNames.forEachIndexed { playerIndex, name ->
//          IQScoreRow(
//           slot = playerIndex+1,
//           colorSlot =
//           when (playersState.characterCards[playerIndex].type)  {
//               CharacterCardType.RED -> Colors.red
//               CharacterCardType.DON -> Color.Gray
//               CharacterCardType.SHERIFF -> Color.Yellow
//               CharacterCardType.BLACK -> Colors.darkGrey51
//               else -> {
//                   Color.Cyan} //If you see this color - app goes wrong
//           },
//           textName = name,
//           rate = 21.92,
//           dops = 11.94,
//           comment = "A comment",
//           modifier = Modifier
//          )
//       }//End of forEach
    //End of the column inside card



  }//End of the first outside column



}//End of ScoreScreenView composable