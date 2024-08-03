package com.example.magnesiumathletesstore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    onPrimary = OnPrimaryColor,
    secondary = SecondaryColor,
    secondaryContainer = SecondaryVariantColor,
    onSecondary = OnSecondaryColor,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.DarkGray,
    onSurface = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    onPrimary = OnPrimaryColor,
    secondary = SecondaryColor,
    secondaryContainer = SecondaryVariantColor,
    onSecondary = OnSecondaryColor,
    background = BackgroundColor,
    onBackground = Color.Black,
    surface = Color.LightGray,
    onSurface = Color.Black
)

private val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    // Add more text styles as needed
)

private val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun MagnesiumAthletesStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
