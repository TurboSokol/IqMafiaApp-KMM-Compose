package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun IQPlayerNameRow(
    modifier: Modifier,
    slot: Int,
    textName: String,
    isInputEnabled: Boolean,
    colorSlot: Color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.65f),
    onSlotClick: (() -> Unit?)? = null,
    //Params for IQDDTE:
    playerNameColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
    allProfilesFromBE: List<ProfileUIModel>,
    profile: ProfileUIModel,
    onProfileChanged: (ProfileUIModel) -> Unit
) {

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.onBackground)

    ) {
        TextButton(
            modifier = Modifier.align(Alignment.CenterVertically).wrapContentWidth().background(color = colorSlot).border(0.5.dp, MaterialTheme.colorScheme.outline),
            onClick = {
                if (onSlotClick != null) {
                    onSlotClick()
                }
            },
            enabled = onSlotClick != null
        ) {
            Text(
                text = (slot + 1).toString(), textAlign = TextAlign.Center,
                fontSize = Dimensions.TextSize.smedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        val playerName = remember { mutableStateOf(textName) }


        IQDropDownTextEdit(
            modifier = modifier,
            allProfilesFromBE = allProfilesFromBE,
            onProfileChanged = onProfileChanged,
            profile = profile,
            playerNameColor = playerNameColor,
        )
        }

    }


