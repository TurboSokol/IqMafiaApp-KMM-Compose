package com.turbosokol.iqmafiaapp.components.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.buttons.IQPrimaryButton
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
fun IQVoteDialogView(
    modifier: Modifier,
    isVisible: Boolean,
    onConfirm: (index: Int) -> Unit,
    onCancel: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
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
                        .padding(Dimensions.Padding.large),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = Dimensions.Padding.medium),
                        text = Strings.voteCountDialogLabel,
                        fontSize = Dimensions.TextSize.medium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(onClick = {
                            onConfirm(1)
                        }) {
                            Text(
                                text = "1",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(2)
                        }) {
                            Text(
                                text = "2",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(3)
                        }) {
                            Text(
                                text = "3",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(onClick = {
                            onConfirm(4)
                        }) {
                            Text(
                                text = "4",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(5)
                        }) {
                            Text(
                                text = "5",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(6)
                        }) {
                            Text(
                                text = "6",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(onClick = {
                            onConfirm(7)
                        }) {
                            Text(
                                text = "7",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(8)
                        }) {
                            Text(
                                text = "8",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(9)
                        }) {
                            Text(
                                text = "9",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    TextButton(onClick = {
                        onConfirm(0)
                    }) {
                        Text(
                            text = "0",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = Dimensions.TextSize.medium
                        )
                    }

                    IQPrimaryButton(
                        modifier = Modifier,
                        text = Strings.tapByMistakeButton,
                        onClick = onCancel
                    )
                }
            }
        }
    }
}


@Composable
fun IQEndVoteDialogView(
    modifier: Modifier,
    isVisible: Boolean,
    onConfirm: (players: List<Int>) -> Unit,
    onCancel: () -> Unit
) {

    val players = remember { mutableStateOf(value = emptyList<Int>()) }
    val is1Pressed = remember { mutableStateOf(value = false) }
    val is2Pressed = remember { mutableStateOf(value = false) }
    val is3Pressed = remember { mutableStateOf(value = false) }
    val is4Pressed = remember { mutableStateOf(value = false) }
    val is5Pressed = remember { mutableStateOf(value = false) }
    val is6Pressed = remember { mutableStateOf(value = false) }
    val is7Pressed = remember { mutableStateOf(value = false) }
    val is8Pressed = remember { mutableStateOf(value = false) }
    val is9Pressed = remember { mutableStateOf(value = false) }
    val is10Pressed = remember { mutableStateOf(value = false) }

    val noOneLeftAlertVisibility = remember { mutableStateOf(value = false) }

    if (isVisible) {
        players.value = emptyList()
        is1Pressed.value = false
        is2Pressed.value = false
        is3Pressed.value = false
        is4Pressed.value = false
        is5Pressed.value = false
        is6Pressed.value = false
        is7Pressed.value = false
        is8Pressed.value = false
        is9Pressed.value = false
        is10Pressed.value = false
        noOneLeftAlertVisibility.value = false
    }

    AnimatedVisibility(visible = isVisible) {
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
                        .padding(Dimensions.Padding.large),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = Dimensions.Padding.medium),
                        text = Strings.endDayDialogLabel,
                        fontSize = Dimensions.TextSize.medium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(
                            onClick = {
                                if (is1Pressed.value) {
                                    players.value = players.value.minus(1)
                                } else {
                                    players.value = players.value.plus(1)
                                }

                                is1Pressed.value = !is1Pressed.value
                            },
                            colors = if (is1Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "1",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is2Pressed.value) {
                                    players.value = players.value.minus(2)
                                } else {
                                    players.value = players.value.plus(2)
                                }

                                is2Pressed.value = !is2Pressed.value
                            }, colors = if (is2Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "2",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is3Pressed.value) {
                                    players.value = players.value.minus(3)
                                } else {
                                    players.value = players.value.plus(3)
                                }

                                is3Pressed.value = !is3Pressed.value
                            }, colors = if (is3Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "3",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(
                            onClick = {
                                if (is4Pressed.value) {
                                    players.value = players.value.minus(4)
                                } else {
                                    players.value = players.value.plus(4)
                                }

                                is4Pressed.value = !is4Pressed.value
                            },
                            colors = if (is4Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "4",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is5Pressed.value) {
                                    players.value = players.value.minus(5)
                                } else {
                                    players.value = players.value.plus(5)
                                }

                                is5Pressed.value = !is5Pressed.value
                            },
                            colors = if (is5Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "5",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is6Pressed.value) {
                                    players.value = players.value.minus(6)
                                } else {
                                    players.value = players.value.plus(6)
                                }

                                is6Pressed.value = !is6Pressed.value
                            },
                            colors = if (is6Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "6",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TextButton(
                            onClick = {
                                if (is7Pressed.value) {
                                    players.value = players.value.minus(7)
                                } else {
                                    players.value = players.value.plus(7)
                                }

                                is7Pressed.value = !is7Pressed.value
                            },
                            colors = if (is7Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "7",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is8Pressed.value) {
                                    players.value = players.value.minus(8)
                                } else {
                                    players.value = players.value.plus(8)
                                }

                                is8Pressed.value = !is8Pressed.value
                            },
                            colors = if (is8Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "8",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(
                            onClick = {
                                if (is9Pressed.value) {
                                    players.value = players.value.minus(9)
                                } else {
                                    players.value = players.value.plus(9)
                                }

                                is9Pressed.value = !is9Pressed.value
                            },
                            colors = if (is9Pressed.value) ButtonDefaults.textButtonColors(
                                MaterialTheme.colorScheme.inversePrimary
                            ) else ButtonDefaults.textButtonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "9",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                            if (is10Pressed.value) {
                                players.value = players.value.minus(10)
                            } else {
                                players.value = players.value.plus(10)
                            }

                            is10Pressed.value = !is10Pressed.value
                        },
                        colors = if (is10Pressed.value) ButtonDefaults.textButtonColors(
                            MaterialTheme.colorScheme.inversePrimary
                        ) else ButtonDefaults.textButtonColors(Color.Transparent)
                    ) {
                        Text(
                            text = "10",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = Dimensions.TextSize.medium
                        )

                    }

                    AnimatedVisibility(visible = noOneLeftAlertVisibility.value) {
                        IQPrimaryButton(
                            modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiary).padding(Dimensions.Padding.medium),
                            text = Strings.noOneLeftAlert,
                            onClick = {})
                    }


                    IQPrimaryButton(
                        modifier = Modifier.padding(top = Dimensions.Padding.small),
                        text = Strings.confirmDayEndButton,
                        onClick = { if (players.value.isNotEmpty()) onConfirm(players.value) else noOneLeftAlertVisibility.value = true }
                    )

                    IQPrimaryButton(
                        modifier = Modifier.padding(top = Dimensions.Padding.small),
                        text = Strings.noOneLeaveButton,
                        onClick = { onConfirm(emptyList()) }
                    )

                    IQPrimaryButton(
                        modifier = Modifier.padding(top = Dimensions.Padding.small),
                        text = Strings.tapByMistakeButton,
                        onClick = onCancel
                    )
                }
            }
        }
    }
}