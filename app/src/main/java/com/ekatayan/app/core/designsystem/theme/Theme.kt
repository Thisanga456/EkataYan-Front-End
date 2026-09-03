package com.ekatayan.app.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    background = EkataBackground,
    surface = EkataCardBackground,
    onBackground = EkataTextPrimary,
    onSurface = EkataTextPrimary,
)
private val DarkColorScheme = darkColorScheme(primary = DarkPrimary)

@Composable
fun EkataYanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = EkataYanTypography,
        content = content,
    )
}
