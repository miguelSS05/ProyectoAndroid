package com.example.proyectomovil.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BluePrimary = Color(0xFF0D47A1)
val BlueLight = Color(0xFF1976D2)
val GreenSaving = Color(0xFF2E7D32)
val Background = Color(0xFFF5F5F5)

private val LightColors = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    secondary = BlueLight,
    background = Background,
    surface = Color.White
)

@Composable
fun TienditiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}