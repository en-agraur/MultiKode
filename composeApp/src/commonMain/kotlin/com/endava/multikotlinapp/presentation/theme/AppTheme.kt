package com.endava.multikotlinapp.presentation.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSpacing = staticCompositionLocalOf { Sizing() }

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacing provides Sizing(),
    ) {
        MaterialTheme(
            colorScheme = if (!useDarkTheme) {
                LightColors
            } else {
                DarkColors
            },
            content = content,
            typography = typography,
            shapes = shapes
        )
    }
}

object AppTheme {
    val spacing: Sizing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
}