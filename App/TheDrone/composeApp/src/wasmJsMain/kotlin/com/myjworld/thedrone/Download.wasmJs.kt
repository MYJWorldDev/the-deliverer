package com.myjworld.thedrone

import kotlinx.browser.window

actual fun downloadApp(url: String) {
    val userAgent = window.navigator.userAgent
    if (
        userAgent.contains("iPhone") ||
        userAgent.contains("iPad") ||
        userAgent.contains("iPod")
//        userAgent.contains("Macintosh") ||
//        userAgent.contains("Mac OS")
        ) {
        window.location.href = "https://example.com"
    } else {
        window.location.href = url
    }
}