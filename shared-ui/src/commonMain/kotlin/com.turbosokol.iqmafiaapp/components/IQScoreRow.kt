package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.components.picker.NumberPicker
import com.turbosokol.iqmafiaapp.theme.Dimensions
import com.turbosokol.iqmafiaapp.theme.Strings


@Composable
fun IQScoreRow(
    modifier: Modifier,
    slot: Int,
    playerColor: Color,
    playerName: String,
    onPlayerNameChanged: (String) -> Unit,
    playerNameColor: Color,
    mainScore: Int,
    onMainPointsChanged: (Int) -> Unit,
    dopPoints: Double,
    onDopsChanged: (Double) -> Unit,
    comment: String,
    onCommentChanged: (String) -> Unit
) {

    val playerNameValue = remember { mutableStateOf(playerName) }
    val mainScoreValue = remember { mutableStateOf(mainScore) }
    val dopsPickerValue = remember { mutableStateOf(dopPoints) }
    val commentValue = remember { mutableStateOf(comment) }


    Card(
        modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
            .padding(Dimensions.Padding.micro),
        elevation = CardDefaults.cardElevation(Dimensions.Elevation.small),
        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = modifier.background(MaterialTheme.colorScheme.onBackground),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = CenterVertically
        ) {


            Box(
                modifier = Modifier
                    .height(Dimensions.Components.IQScoreRow.rowHeight)
                    .weight(0.07f)
                    .background(color = playerColor)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = Dimensions.Padding.smedium,
                        top = Dimensions.Padding.xsmall
                    ),
                    text = slot.toString(),
                    style = TextStyle(color = playerNameColor),
                    textAlign = TextAlign.Center
                )
            }

            //NAME
            Box(
                modifier = Modifier
                    .height(Dimensions.Components.IQScoreRow.rowHeight)
                    .weight(0.23f)
                    .background(color = playerColor)
                    .padding(top = 0.dp, bottom = 0.dp, end = Dimensions.Padding.small)
                    .align(CenterVertically),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    modifier = Modifier,
                    textStyle = TextStyle(color = playerNameColor, textAlign = TextAlign.Start),
                    value = playerNameValue.value, onValueChange = { changedValue: String ->
                        playerNameValue.value = changedValue
                        if (playerNameValue.value == changedValue) {
                            onPlayerNameChanged(changedValue)
                        }
                    }
                )
            }


            //MAIN SCORE PICKER
            NumberPicker(
                value = mainScoreValue.value,
                range = listOf(0, 1),
                onValueChange = { changedValue ->
                    mainScoreValue.value = changedValue
                    if (mainScoreValue.value == changedValue) onMainPointsChanged(mainScoreValue.value)

                }
            )

            //DOPS PICKER
            NumberPicker(
                modifier = Modifier.padding(0.dp, 0.dp, 25.dp, 0.dp),
                value = dopsPickerValue.value,
                range = listOf(
                    -0.5,
                    -0.4,
                    -0.3,
                    -0.2,
                    -0.1,
                    0.0,
                    0.1,
                    0.2,
                    0.3,
                    0.4,
                    0.5,
                    0.6,
                    0.7,
                    0.8,
                    0.9,
                    1.0,
                    1.1
                ),
                onValueChange = { changedValue ->
                    dopsPickerValue.value = changedValue
                    if (dopsPickerValue.value == changedValue) onDopsChanged(changedValue)
                }
            )

            //COMMENT
            BasicTextField(
                value = commentValue.value,
                onValueChange = { changedValue: String ->
                    commentValue.value = changedValue
                    onCommentChanged(changedValue)
                },
                minLines = 1,
                maxLines = 4,
                modifier = Modifier.height(Dimensions.Components.IQScoreRow.rowHeight)/*.border(0.5.dp, MaterialTheme.colorScheme.outline)*/
                    .weight(0.32f).padding(0.dp, 0.dp, 5.dp, 0.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Start
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary),
                decorationBox = { innerTextField ->
                    if (commentValue.value.isEmpty()) {
                        Text(
                            text = Strings.commentHint,
                            fontSize = Dimensions.TextSize.ssmall,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                        )
                    }
                    innerTextField()
                }
            )
        }
    }

}