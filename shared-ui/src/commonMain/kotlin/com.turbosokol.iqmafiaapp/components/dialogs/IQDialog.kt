package com.turbosokol.iqmafiaapp.components.dialogs

import androidx.compose.runtime.Composable

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/


@Composable
expect fun IQDialog(dismiss: () -> Unit, content: @Composable () -> Unit)