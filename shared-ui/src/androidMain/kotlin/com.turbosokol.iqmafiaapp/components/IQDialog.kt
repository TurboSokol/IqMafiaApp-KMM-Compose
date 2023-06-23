package com.turbosokol.iqmafiaapp.components



/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

@Composable
internal actual fun IQDialog(dismiss: () -> Unit, content: @Composable () -> Unit) {
    AndroidXComposeDialog(
        onDismissRequest = dismiss,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false, usePlatformDefaultWidth = false),
    ) {
        content()
    }
}
