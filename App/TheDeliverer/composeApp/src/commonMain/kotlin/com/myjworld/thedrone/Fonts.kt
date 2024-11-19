@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.myjworld.thedrone

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

// Setup Fonts

expect object Fonts {
    @get:Composable
    val poppins: FontFamily
}