package com.turbosokol.iqmafiaapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


/***
 *If this code runs it created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who create it.
 ***/


@Composable
internal fun IqMafiaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {



    val colors = if (darkTheme) {
        DarkColors //DarkColorPalette
    } else {
        LightColors //secLightPallete
    }



    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = typography,
        shapes = shapes
    )
}

private val LightColors = lightColorScheme(
    primary = Colors.md_theme_light_primary,
    onPrimary = Colors.md_theme_light_onPrimary,
    inversePrimary = Colors.md_theme_light_inversePrimary,
    primaryContainer = Colors.md_theme_light_primaryContainer,
    secondary = Colors.md_theme_light_secondary,
    secondaryContainer = Colors.md_theme_light_secondaryContainer,
    tertiary = Colors.md_theme_light_tertiary,
    background = Colors.md_theme_light_background,
    onBackground = Colors.md_theme_light_onBackground,
)


private val DarkColors = darkColorScheme(

)