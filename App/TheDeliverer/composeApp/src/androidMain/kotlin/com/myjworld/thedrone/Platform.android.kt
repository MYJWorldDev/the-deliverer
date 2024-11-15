package com.myjworld.thedrone

import android.content.res.Configuration
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getSize(): String {
    val configuration = LocalConfiguration.current
    return if (configuration.screenWidthDp > 840 && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        "Large"
    } else {
        "Small"
    }
}

actual fun getPlatform(): String {
    return "Android ${Build.VERSION.SDK_INT}"
}