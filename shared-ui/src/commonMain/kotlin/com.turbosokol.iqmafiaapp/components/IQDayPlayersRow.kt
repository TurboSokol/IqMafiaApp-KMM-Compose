package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IQDayPlayersRow(
    slot: Int,
    onSlotClick: (() -> Unit?)? = null,
    colorSlot: Color = Colors.primary.copy(alpha = 0.65f),
    textName: String,
    isNameInputEnabled: Boolean,
    colorName: Color = Colors.orange.copy(alpha = 0.1f),
    colorFault: Color,
    textFault: String,
    onFaultClick: () -> Unit,
    onNameChanged: (String) -> Unit
) {
    Row(modifier = Modifier.background(color = colorFault).border(
        BorderStroke(0.5.dp, color = Colors.gray)
    )) {
        IQPlayerNameRow(modifier = Modifier.weight(0.85f), slot, textName, isNameInputEnabled, colorSlot, colorName, onSlotClick, onNameChanged)
        TextButton(
            onClick = { onFaultClick() },
            modifier = Modifier.align(Alignment.CenterVertically).wrapContentWidth().weight(0.15f),
            enabled = onSlotClick != null
        ) {
            Text(
                text = textFault,
                textAlign = TextAlign.Center,
                fontSize = Dimensions.TextSize.smedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}