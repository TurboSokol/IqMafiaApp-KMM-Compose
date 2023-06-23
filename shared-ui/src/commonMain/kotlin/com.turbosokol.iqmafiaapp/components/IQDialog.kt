package com.turbosokol.iqmafiaapp.components

import androidx.compose.runtime.Composable

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/


@Composable
internal expect fun IQDialog(dismiss: () -> Unit, content: @Composable () -> Unit)