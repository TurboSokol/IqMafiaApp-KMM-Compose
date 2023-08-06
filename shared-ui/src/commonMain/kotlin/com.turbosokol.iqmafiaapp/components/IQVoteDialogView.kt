package com.turbosokol.iqmafiaapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.turbosokol.iqmafiaapp.features.judge.analytics.round.RoundAction
import com.turbosokol.iqmafiaapp.theme.Colors
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
    label: String,
    type: IQVoteDialogType,
    onConfirm: (index: Int) -> Unit,
    onCancel: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        IQDialog(dismiss = onCancel) {
            Surface(
                shape = RoundedCornerShape(Dimensions.CornerRadius.xlarge),
                elevation = Dimensions.Elevation.xlarge,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(Dimensions.Padding.large),
            ) {
                Column(
                    modifier = Modifier
                        .padding(Dimensions.Padding.medium)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(bottom = Dimensions.Padding.medium),
                        text = label,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle1,
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
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(2)
                        }) {
                            Text(
                                text = "2",
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(3)
                        }) {
                            Text(
                                text = "3",
                                color = Colors.secondary,
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
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(5)
                        }) {
                            Text(
                                text = "5",
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(6)
                        }) {
                            Text(
                                text = "6",
                                color = Colors.secondary,
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
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(8)
                        }) {
                            Text(
                                text = "8",
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }

                        TextButton(onClick = {
                            onConfirm(9)
                        }) {
                            Text(
                                text = "9",
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(onClick = {
                            onConfirm( when (type) {
                                IQVoteDialogType.VOTE_RESULTS -> 0
                                IQVoteDialogType.ROUND_RESULTS -> 10
                            })
                        }) {
                            Text(
                                text = when (type) {
                                    IQVoteDialogType.VOTE_RESULTS -> "0"
                                    IQVoteDialogType.ROUND_RESULTS -> "10"
                                },
                                color = Colors.secondary,
                                fontSize = Dimensions.TextSize.medium
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class IQVoteDialogType {
    //WHO IS VOTE LEADER
    ROUND_RESULTS,

    //HOW MANY VOTES FOR EACH CANDIDATE
    VOTE_RESULTS
}