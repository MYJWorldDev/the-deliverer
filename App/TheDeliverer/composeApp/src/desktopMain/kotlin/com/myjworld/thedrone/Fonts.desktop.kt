package com.myjworld.thedrone

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.poppins_medium
import thedeliverer.composeapp.generated.resources.poppins_regular
import thedeliverer.composeapp.generated.resources.poppins_semibold

actual object Fonts {
    actual val poppins: FontFamily
        @Composable
        get() = FontFamily(
            Font(
                resource = Res.font.poppins_regular,
                weight = FontWeight(FontWeight.Normal.weight)
            ),
            Font(
                resource = Res.font.poppins_medium,
                weight = FontWeight(FontWeight.Medium.weight)
            ),
            Font(
                resource = Res.font.poppins_semibold,
                weight = FontWeight(FontWeight.SemiBold.weight)
            )
        )
}