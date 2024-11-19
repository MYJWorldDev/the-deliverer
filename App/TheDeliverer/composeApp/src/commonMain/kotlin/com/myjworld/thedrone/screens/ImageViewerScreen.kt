package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myjworld.thedrone.AppData
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.PreviousButton
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.imageResource

@Composable
fun ImageViewerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        val imageToShow = AppData.getImage()
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Spacer(Modifier.width(15.dp))
            PreviousButton {
                navigation.navigate(AppData.getReturnRoute())
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            bitmap = imageResource(imageToShow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Spacer(modifier = Modifier.height(70.dp))
    }
}