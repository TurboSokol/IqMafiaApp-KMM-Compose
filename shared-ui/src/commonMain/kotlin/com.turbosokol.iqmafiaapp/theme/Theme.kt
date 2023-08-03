package com.turbosokol.iqmafiaapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/

private val DarkColorPalette = darkColors(
    primary = Colors.primary,
    primaryVariant = Colors.primaryVariant,
    secondary = Colors.secondaryLighter,
    secondaryVariant = Colors.secondaryLighter,
    surface = Colors.darkGrey51,
    background = Colors.darkGrey32,
    error = Colors.orange,
    onPrimary = Colors.secondary,
    onSecondary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Colors.primary,
    primaryVariant = Colors.primaryVariant,
    secondary = Colors.secondary,
    // color of the switch (checked)
    secondaryVariant = Colors.secondaryVariant,
    // color of the switch (unchecked)
    surface = Colors.lightGray,
    background = Colors.white,
    error = Colors.orange,
    onPrimary = Colors.secondary,
    onSecondary = Color.White,
    onSurface = Color.Black,
    onBackground = Colors.darkBlue,
)

val secLightPallete = lightColors(
    primary = Color(0xFFB0BEC5), // Светло-синий цвет
    primaryVariant = Color(0xFF90A4AE), // Светлый серо-синий цвет
    secondary = Color(0xFFA5D6A7), // Светло-зеленый цвет
    secondaryVariant = Color(0xFF81C784), // Зеленый цвет
    surface = Color(0xFFF5F5F5), // Белый цвет с небольшим оттенком серого
    background = Color(0xFFFFFFFF), // Чистый белый цвет
    error = Color(0xFFEF5350), // Красный цвет
    onPrimary = Color(0xFF757575), // Средне-серый цвет
    onSecondary = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black, // Темно-синий цвет
)

@Composable
internal fun IqMafiaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        secLightPallete
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography,
        shapes = shapes
    )
}

