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

private val DarkColorPalette = darkColorScheme(
    primary = Colors.primary,
    primaryContainer = Colors.primaryVariant,
    secondary = Colors.secondaryLighter,
    secondaryContainer = Colors.secondaryLighter,
    surface = Colors.darkGrey51,
    background = Colors.darkGrey32,
    error = Colors.orange,
    onPrimary = Colors.secondary,
    onSecondary = Colors.white,
    onSurface = Colors.white,
    onBackground = Colors.white,
)

private val LightColorPalette = lightColorScheme(
    primary = Colors.primary,
    primaryContainer = Colors.primaryVariant,
    secondary = Colors.secondary,
    // color of the switch (checked)
    secondaryContainer = Colors.secondaryVariant,
    // color of the switch (unchecked)
    surface = Colors.lightGray,
    background = Colors.white,
    error = Colors.orange,
    onPrimary = Colors.secondary,
    onSecondary = Colors.white,
    onSurface = Colors.darkGrey51,
    onBackground = Colors.darkBlue,
)

//val secLightPallete = lightColors(
//    primary = Color(0xFFB0BEC5), // Светло-синий цвет
//    primaryVariant = Color(0xFF90A4AE), // Светлый серо-синий цвет
//    secondary = Color(0xFFA5D6A7), // Светло-зеленый цвет
//    secondaryVariant = Color(0xFF81C784), // Зеленый цвет
//    surface = Color(0xFFF5F5F5), // Белый цвет с небольшим оттенком серого
//    background = Color(0xFFFFFFFF), // Чистый белый цвет
//    error = Color(0xFFEF5350), // Красный цвет
//    onPrimary = Color(0xFF757575), // Средне-серый цвет
//    onSecondary = Colors.white,
//    onSurface = Colors.darkGrey51,
//    onBackground = Colors.darkGrey51, // Темно-синий цвет
//)

@Composable
internal fun IqMafiaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette    //DarkColorPalette
    } else {
        LightColorPalette  //secLightPallete
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = typography,
        shapes = shapes
    )
}

//m3

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)