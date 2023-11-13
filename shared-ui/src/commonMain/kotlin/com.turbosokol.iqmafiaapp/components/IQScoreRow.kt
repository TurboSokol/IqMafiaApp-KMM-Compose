package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.picker.NumberPicker
import com.turbosokol.iqmafiaapp.theme.Dimensions


@Composable
fun IQScoreRow(
    modifier: Modifier,
    slot: Int,
    playerColor: Color,
    playerName: String,
    onPlayerNameChanged: (String) -> Unit,
    playerNameColor: Color,
    mainScore: String,
    onMainPointsChanged: (Int) -> Unit,
    dopPoints: Double,
    onDopsChanged: (Double) -> Unit,
    comment: String,
    onCommentChanged: (String) -> Unit
) {

    val playerNameValue = remember { mutableStateOf(playerName) }
    val mainScoreValue = remember { mutableStateOf(mainScore) }
    val dopsPickerValue = remember { mutableStateOf(0.3) }


    Card(
        modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
            .padding(Dimensions.Padding.micro),
        elevation = CardDefaults.cardElevation(Dimensions.Elevation.small),
        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = slot.toString(),
                style = TextStyle(color = playerNameColor),
                textAlign = TextAlign.Center,
                modifier = modifier.height(70.dp).weight(0.07f).background(color = playerColor)
                    .align(Alignment.CenterVertically)
            )

            //NAME
            BasicTextField(
                modifier = Modifier.height(70.dp).weight(0.23f).background(color = playerColor).padding(0.dp, 20.dp)/*.align(CenterVertically)*/,
                textStyle = TextStyle(color = playerNameColor),
                value = playerNameValue.value, onValueChange = { changedValue: String ->
                    playerNameValue.value = changedValue
                    if (playerNameValue.value == changedValue) {
                        onPlayerNameChanged(changedValue)
                    }
                }
            )

            //SLOT


            //MAIN SCORE
            Text(
                text = mainScoreValue.value,
                modifier = modifier/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/.weight(0.15f)
                    .clickable(
                enabled = true,
                onClick = {
                    if (mainScoreValue.value.toInt() == 0) {mainScoreValue.value = 1.toString()}
                    else if (mainScoreValue.value.toInt() == 1) {mainScoreValue.value = 0.toString()}
//                    mainScoreValue.value = 1.toString()
                }
                    ),
            )
//                    TODO():: NO MORE THAN 1 length
//                onValueChange = { changedValue ->
//                    mainScoreValue.value = changedValue
//                    if (changedValue.isNotBlank()) {
//                        onMainPointsChanged(changedValue.toInt())
//                    }
//                }


            //DOPS PICKER
            NumberPicker(
                modifier = Modifier,
                value = dopsPickerValue.value,
                range = listOf(
                    -0.5,
                    -0.4,
                    -0.3,
                    -0.2,
                    -0.1,
                    0.0,
                    0.1,
                    0.2,
                    0.3,
                    0.4,
                    0.5,
                    0.6,
                    0.7,
                    0.8,
                    0.9,
                    1.0,
                    1.1
                ),
                onValueChange = { changedValue: Double ->
                    dopsPickerValue.value = changedValue
//                    onDopsChanged(changedValue)
                }
            )

            //COMMENT
            BasicTextField(
                value = comment,
                onValueChange = {},
                minLines = 2,
                maxLines = 2,
                modifier = Modifier.wrapContentHeight()/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/
                    .weight(0.32f)
            )
        }
    }

}
