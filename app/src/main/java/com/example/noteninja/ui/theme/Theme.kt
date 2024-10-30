package com.example.noteninja.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF000000),
    secondary = Color(0xFF2B2929),
    tertiary = Color(0xFF8A8C8C),
    onSurface = Color.White,
    //TO BE USED FOR BUTTON ONLY
    background = Color(0xFF8A8C8C)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00C1B4),
    secondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFFA4E5E0),
    onSurface = Color.Black,
    //TO BE USED FOR BUTTON ONLY
    background = Color(0xFF00C1B4)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun NoteNinjaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,

        content = content
    )
}