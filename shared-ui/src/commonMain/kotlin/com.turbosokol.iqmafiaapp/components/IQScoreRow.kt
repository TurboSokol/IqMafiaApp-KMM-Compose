package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

@Composable
fun IQScoreRow(
    modifier: Modifier,

    slot : Int, // № of a slot
    colorSlot : Color,

    textName : String,

    cardType: CharacterCardType,
    cardColor: Color,

    textResult : String,  //win or lose

    rate : Double
    )
{
        Row(modifier = modifier.border(
            BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline)))
        {
            Text(text = slot.toString(), modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).background(color = colorSlot))
            Text(text = textName, modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline))
            Text(text = cardType.toString(), modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).background(cardColor))
            Text(text = textResult, modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline))
            Text(text = rate.toString(), modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline))
        }



}