package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.window.PopupProperties

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who was created it.
 ***/
@Composable
actual fun IQDropdownMenu(
    isExpanded: Boolean,
    dismiss: () -> Unit,
    modifier: Modifier,
    offset: DpOffset,
    properties: PopupProperties,
    content: @Composable() ColumnScope.() -> Unit
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = dismiss,
        modifier = modifier,
        offset = offset,
        properties = properties,
        content = content
    )
}