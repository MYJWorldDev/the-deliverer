package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myjworld.thedrone.AppData
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.DecorLine
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.HomeButton
import com.myjworld.thedrone.NextButton
import com.myjworld.thedrone.PreviousButton
import com.myjworld.thedrone.Title
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.map

@Composable
fun MapScreen() {
    @Composable
    fun ShowImage(
        image: DrawableResource
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(25.dp))
                .background(Colors.background)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Click to view the map",
                color = Colors.onPrimaryVariant,
                modifier = Modifier.padding(25.dp).clickable {
                    AppData.setImage(Res.drawable.map)
                    AppData.setReturnRoute("map")
                    navigation.navigate("imageViewer")
                },
                fontFamily = poppins,
                fontSize = 30.sp
            )
            Spacer(Modifier.height(10.dp))
            Image(
                bitmap = imageResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp))
                    .clickable {
                        AppData.setImage(Res.drawable.map)
                        AppData.setReturnRoute("map")
                        navigation.navigate("imageViewer")
                    },
                contentScale = ContentScale.Crop,

                )
        }
        Spacer(Modifier.height(10.dp))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary)
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            text = "The Map",
            color = Colors.onPrimaryVariant
        )
        DecorLine()
        ShowImage(
            image = Res.drawable.map
        )
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            PreviousButton {
                navigation.navigate("features")
            }
            Spacer(Modifier.width(10.dp))
            HomeButton()
            Spacer(Modifier.width(10.dp))
            NextButton {
                navigation.navigate("prospects")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}