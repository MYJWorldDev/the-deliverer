package com.myjworld.thedrone

import androidx.compose.runtime.Composable
import kotlinx.browser.window

actual fun getPlatform(): String {
    return "Web with Kotlin/Wasm"
}

@Composable
actual fun getSize(): String {
    val userAgent = window.navigator.userAgent
    return  if (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("iPhone")) {
        "Small"
    } else {
        "Large"
    }
}