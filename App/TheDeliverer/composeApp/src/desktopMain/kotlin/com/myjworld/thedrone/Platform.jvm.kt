package com.myjworld.thedrone

import androidx.compose.runtime.Composable

actual fun getPlatform(): String {
    return "Java ${System.getProperty("java.version")}"
}

@Composable
actual fun getSize(): String {
    return "Large"
}