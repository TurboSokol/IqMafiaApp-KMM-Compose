package com.turbosokol.iqmafiaapp.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Dialog

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
actual fun IQDialog(dismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(
        onDismissRequest = dismiss,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false, usePlatformDefaultWidth = false),
    ) {
        content()
    }
}