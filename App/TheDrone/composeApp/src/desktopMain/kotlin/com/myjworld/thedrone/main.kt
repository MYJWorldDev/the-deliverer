package com.myjworld.thedrone

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import thedrone.composeapp.generated.resources.Res
import thedrone.composeapp.generated.resources.ic

fun main() = application {
    val icon = painterResource(Res.drawable.ic)
    val windowState = rememberWindowState(
        placement = WindowPlacement.Maximized
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "The Drone",
        icon = icon,
        resizable = false,
        state = windowState
    ) {
        App()
    }
}