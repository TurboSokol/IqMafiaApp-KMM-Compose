package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun IQPlayerNameRow(
    modifier: Modifier,
    slot: Int,
    textName: String,
    isInputEnabled: Boolean,
    colorSlot: Color = Colors.primary.copy(alpha = 0.65f),
    colorName: Color = Colors.orange.copy(alpha = 0.1f),
    onSlotClick: (() -> Unit?)? = null,
    onTextChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .border(0.5.dp, Colors.gray)
            .background(color = Color.White)
            .background(color = colorSlot)
    ) {
        TextButton(
            onClick = {
                if (onSlotClick != null) {
                    onSlotClick()
                }
            },
            modifier = Modifier.align(Alignment.CenterVertically).wrapContentWidth(),
            enabled = onSlotClick != null
        ) {
            Text(
                text = (slot + 1).toString(), textAlign = TextAlign.Center,
                fontSize = Dimensions.TextSize.smedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        var playerName by remember { mutableStateOf(textName) }

        OutlinedTextField(
            value = playerName,
            modifier = Modifier.weight(0.85f)
                .background(Colors.white)
                .background(color = colorName),
            onValueChange = { changedValue: String ->
                playerName = changedValue
                if (playerName == changedValue) {
                    onTextChanged(changedValue)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = if (slot == 9) ImeAction.Done else ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            textStyle = TextStyle(fontSize = Dimensions.TextSize.smedium),
            singleLine = true,
            readOnly = !isInputEnabled,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent))
    }
}