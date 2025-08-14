package com.myjworld.thedrone

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    removeLoadingSpinner()
    ComposeViewport(document.body!!) {
        App()
    }
}

fun removeLoadingSpinner() {
    val spinnerImg = document.getElementById("loading-logo") as? HTMLElement
    spinnerImg?.style?.display = "none"
    val spinner = document.getElementById("loading-spinner") as? HTMLElement
    spinner?.style?.display = "none"
}