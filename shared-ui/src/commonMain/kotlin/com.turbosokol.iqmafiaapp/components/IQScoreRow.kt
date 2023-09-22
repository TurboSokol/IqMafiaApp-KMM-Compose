package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    dops : Double,

    rate : Double,

    comment : String
    )
{
        Row(modifier = modifier.border(
            BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline))
            .fillMaxWidth()
            ,horizontalArrangement = Arrangement.Start
            , verticalAlignment = Alignment.Top
        )
        {
            Text(text = slot.toString(), modifier = modifier.background(color = colorSlot).width(23.dp))
            Text(text = textName, modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline).width(115.dp))
            Text(text = rate.toString())// ,modifier = modifier.width(25.dp))
            Text(text = dops.toString(), modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline))
            Text(text = comment , modifier = modifier.width(115.dp))//, modifier = modifier.border(0.5.dp, MaterialTheme.colorScheme.outline))
        }



}