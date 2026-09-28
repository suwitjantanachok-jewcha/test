package com.example.ui.theme

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
    primary = BibleDarkPrimary,
    onPrimary = BibleDarkOnPrimary,
    primaryContainer = BibleDarkPrimaryContainer,
    onPrimaryContainer = BibleDarkOnPrimaryContainer,
    secondary = BibleDarkSecondary,
    onSecondary = BibleDarkOnSecondary,
    secondaryContainer = BibleDarkSecondaryContainer,
    onSecondaryContainer = BibleDarkOnSecondaryContainer,
    tertiary = BibleDarkTertiary,
    background = BibleDarkBackground,
    surface = BibleDarkSurface,
    surfaceVariant = BibleDarkSurfaceVariant,
    onBackground = Color(0xFFF1F5F9),
    onSurface = Color(0xFFF8FAFC)
)

private val LightColorScheme = lightColorScheme(
    primary = BibleNavyPrimary,
    onPrimary = BibleNavyOnPrimary,
    primaryContainer = BibleNavyContainer,
    onPrimaryContainer = BibleNavyOnContainer,
    secondary = BibleGoldSecondary,
    onSecondary = BibleGoldOnSecondary,
    secondaryContainer = BibleGoldContainer,
    onSecondaryContainer = BibleGoldOnContainer,
    tertiary = BibleTealTertiary,
    tertiaryContainer = BibleTealContainer,
    onTertiaryContainer = BibleTealOnContainer,
    background = BibleLightBackground,
    surface = BibleLightSurface,
    surfaceVariant = BibleLightSurfaceVariant,
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF1E293B)
)

@Composable
fun BibleQuizTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep distinctive biblical navy & gold aesthetic
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
