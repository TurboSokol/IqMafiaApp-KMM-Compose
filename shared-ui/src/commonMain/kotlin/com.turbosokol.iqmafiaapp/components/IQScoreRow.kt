package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun IQScoreRow(
    modifier: Modifier,

    slot : Int, // № of a slot
    colorSlot : Color,

    textName : String,
    playerNameColor : Color,
    onTextChanged: (String) -> Unit,

    dops : String,
    onDopsChanged : (String) ->Unit,
    rate : Double,

    comment : String
    )
{
    var playerName by remember { mutableStateOf(textName) }
    var dopsState by remember { mutableStateOf(dops) }
    var mySelection by remember { mutableStateOf(TextRange(dopsState.length)) }
    val expression = Regex("[\\d,]*[.]?[\\d,]*")

    var textFieldValueState =
            TextFieldValue(
                text = dopsState,
                selection = mySelection,
//                composition = TextRange(0,2)
            )




        Row(modifier = modifier.border(
            BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline))
            .fillMaxWidth()
            ,horizontalArrangement = Arrangement.Start
            , verticalAlignment = Alignment.Top
        )
        {
            Text(text = slot.toString(), modifier = modifier.fillMaxWidth(0.06f).weight(0.08f))

            BasicTextField(value = playerName, onValueChange = {
                    changedValue: String ->
                        playerName = changedValue
                if (playerName == changedValue) {
                    onTextChanged(changedValue)
                }
            },
                modifier = modifier/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/.weight(0.3f).background(color = colorSlot)
                , textStyle = TextStyle(playerNameColor)
                 )

            Text(text = " $rate ", modifier = modifier/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/.weight(0.15f))

            //DOPS BTF
            BasicTextField(value = textFieldValueState,
                onValueChange =  {
                    changedValue ->
                   dopsState = changedValue.text

            }
                , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                , modifier = modifier/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/.weight(0.15f))
            //End of Dops BTF


            BasicTextField(value = comment , {}, modifier = modifier/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/.weight(0.32f))
        }



}