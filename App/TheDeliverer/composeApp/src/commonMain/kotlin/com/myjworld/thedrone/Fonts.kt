package com.myjworld.thedrone

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

expect object Fonts {
    @get:Composable
    val poppins: FontFamily
}