package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.data.profile.ProfileUIModel
import com.turbosokol.iqmafiaapp.theme.Dimensions

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/


@Composable
fun IQDayPlayersRow(
    slot: Int,
    onSlotClick: (() -> Unit?)? = null,
    colorSlot: Color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.65f),
    textName: String,
    isNameInputEnabled: Boolean,
//    colorName: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
    colorFault: Color,
    textFault: String,
    onFaultClick: () -> Unit,

    profile: ProfileUIModel,
    onProfileChanged: (ProfileUIModel) -> Unit,
    allProfilesFromBE: List<ProfileUIModel>
) {
    Row(modifier = Modifier.background(color = colorFault).border(
        BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline)
    )) {
        IQPlayerNameRow(modifier = Modifier.weight(0.85f),
            slot = slot, textName = textName, isInputEnabled = isNameInputEnabled,
            colorSlot = colorSlot, onSlotClick = onSlotClick,allProfilesFromBE = allProfilesFromBE,
        onProfileChanged = onProfileChanged, profile = profile)

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
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}