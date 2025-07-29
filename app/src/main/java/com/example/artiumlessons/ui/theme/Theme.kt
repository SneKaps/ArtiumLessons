package com.example.artiumlessons.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.artiumlessons.ui.setupRootViewInsetsAndStatusBarColor


private val LightColorScheme = lightColorScheme(
    primary = WHITEFE,
    surface = GRAYF7,
    onPrimary = BLACK19,
    onSurface = BLACK19
)

@Composable
fun ArtiumLessonsTheme(
    content: @Composable () -> Unit
) {

    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.setupRootViewInsetsAndStatusBarColor(colorScheme.surface.toArgb())
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}