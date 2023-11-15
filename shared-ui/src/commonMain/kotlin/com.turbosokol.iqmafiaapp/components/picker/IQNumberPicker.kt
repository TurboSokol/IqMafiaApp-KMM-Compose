package com.turbosokol.iqmafiaapp.components.picker

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun <T> NumberPicker(
    modifier: Modifier = Modifier,
    label: (T) -> String = {
        it.toString()
    },
    value: T,
    onValueChange: (T) -> Unit,
    dividersColor: Color = MaterialTheme.colorScheme.primary,
    range: List<T>,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    ListItemPicker(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        dividersColor = dividersColor,
        list = range,
        textStyle = textStyle
    )
}