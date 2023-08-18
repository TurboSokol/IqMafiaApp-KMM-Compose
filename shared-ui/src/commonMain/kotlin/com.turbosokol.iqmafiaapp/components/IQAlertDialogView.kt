package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
        IQDialog(dismiss = onCancel) {
            Surface(
                shape = RoundedCornerShape(Dimensions.CornerRadius.xlarge),
                shadowElevation = Dimensions.Elevation.xlarge,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(Dimensions.Padding.large),
            ) {
                Column(
                    modifier = Modifier
                        .padding(Dimensions.Padding.medium)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = label,
//                        color = MaterialTheme.colors.onBackground, //TODO: Delete if OK
                        color = MaterialTheme.colorScheme.background,
//                        style = MaterialTheme.typography.subtitle1,
                        style = MaterialTheme.typography.titleSmall,//TODO: Delete if OK
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(onClick = onCancel) {
                            Text(
                                text = "NO",
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = onConfirm) {
                            Text(
                                text = "YES",
                                color = Colors.primary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }
                }
            }
        }
    }
}