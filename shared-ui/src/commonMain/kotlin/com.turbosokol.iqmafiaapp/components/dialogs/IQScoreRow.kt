package com.turbosokol.iqmafiaapp.components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.data.character_card.CharacterCardType

@Composable
fun IQScoreRow(
    modifier: Modifier,

    slot : Int,
    colorSlot : Color,

    textName : String,

    cardType: CharacterCardType,
    cardColor: Color,

    textResult : String,

    rate : Double
    )
{
        Row(modifier = modifier.border(
            BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline)))
        {

        }
}