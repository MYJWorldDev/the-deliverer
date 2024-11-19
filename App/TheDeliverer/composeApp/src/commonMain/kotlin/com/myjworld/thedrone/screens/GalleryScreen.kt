package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myjworld.thedrone.AppData
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.DecorLine
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.HomeButton
import com.myjworld.thedrone.NextButton
import com.myjworld.thedrone.PreviousButton
import com.myjworld.thedrone.Title
import com.myjworld.thedrone.getSize
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.back
import thedeliverer.composeapp.generated.resources.bg
import thedeliverer.composeapp.generated.resources.front
import thedeliverer.composeapp.generated.resources.side
import thedeliverer.composeapp.generated.resources.top

@Composable
fun GalleryScreen() {
    @Composable
    fun ShowImage(
        image: DrawableResource
    ) {
        Column(
            modifier = Modifier
                .width(375.dp)
                .height(325.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Colors.background)
                .clickable {
                    AppData.setImage(image = image)
                    AppData.setReturnRoute("gallery")
                    navigation.navigate("imageViewer")
                }
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                bitmap = imageResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    AppData.setImage(image = image)
                    AppData.setReturnRoute("gallery")
                    navigation.navigate("imageViewer")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors.primary,
                    contentColor = Colors.onPrimary
                ),
                modifier = Modifier
                    .width(250.dp)
                    .clip(RoundedCornerShape(100.dp))
            ) {
                Spacer(Modifier.height(40.dp))
                Image(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    colorFilter = ColorFilter.tint(Colors.onPrimary)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "View Image",
                    color = Colors.onPrimary,
                    fontSize = typography.h6.fontSize,
                    style = typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins
                )
            }
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
            text = "Gallery",
            color = Colors.onPrimaryVariant
        )
        DecorLine()
        if (getSize() == "Small") {
            ShowImage(
                image = Res.drawable.front
            )
            ShowImage(
                image = Res.drawable.back
            )
            ShowImage(
                image = Res.drawable.side
            )
            ShowImage(
                image = Res.drawable.top
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShowImage(
                    image = Res.drawable.front
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.back
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.side
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.top
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            PreviousButton {
                navigation.navigate("prospects")
            }
            Spacer(Modifier.width(10.dp))
            HomeButton()
            Spacer(Modifier.width(10.dp))
            NextButton {
                navigation.navigate("home")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}