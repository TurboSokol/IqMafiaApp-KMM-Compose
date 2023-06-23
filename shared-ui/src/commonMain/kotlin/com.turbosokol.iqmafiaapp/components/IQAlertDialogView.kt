package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Colors
import com.turbosokol.iqmafiaapp.theme.Dimensions

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun IQAlertDialogView(
    modifier: Modifier,
    isVisible: Boolean,
    label: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(),
        exit = fadeOut()
    ) {
        Card(elevation = 8.dp, backgroundColor = Colors.primary, modifier = modifier) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = label, fontSize = Dimensions.TextSize.huge, color = Colors.secondary)
                Row {
                    Button(onClick = onConfirm) {
                        Text(text = "Yes", color = Colors.secondary)
                    }

                    Button(onClick = onCancel) {
                        Text(text = "No", color = Colors.secondary)
                    }
                }
            }
        }

    }
}