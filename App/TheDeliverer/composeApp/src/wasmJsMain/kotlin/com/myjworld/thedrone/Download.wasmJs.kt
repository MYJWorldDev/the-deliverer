package com.myjworld.thedrone

import kotlinx.browser.window

actual fun downloadApp(url: String): Boolean {
    val userAgent = window.navigator.userAgent
    return if (
        userAgent.contains("iPhone") ||
        userAgent.contains("iPad") ||
        userAgent.contains("iPod") ||
        userAgent.contains("Macintosh") ||
        userAgent.contains("Mac OS")
        ) {
        false
    } else {
        window.location.href = url
        true
    }
}