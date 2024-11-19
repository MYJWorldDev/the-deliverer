package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.DecorLine
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.HomeButton
import com.myjworld.thedrone.NextButton
import com.myjworld.thedrone.PreviousButton
import com.myjworld.thedrone.Title
import com.myjworld.thedrone.getSize
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.bg

@Composable
fun FeaturesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary)
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            text = "Features",
            color = Colors.onPrimaryVariant
        )
        DecorLine()

        @Composable
        fun Feature(
            number: String,
            description: String
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Colors.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = number,
                        color = Colors.onPrimaryVariant,
                        fontSize = 30.sp,
                        style = typography.h4,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins
                    )
                }
                Spacer(Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(Colors.primaryVariant)
                        .padding(15.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = description,
                        color = Colors.onPrimary,
                        fontSize = 18.sp,
                        style = typography.h4,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppins
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        if (getSize() == "Small") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Feature(
                    number = "1",
                    description = "ACAS - Auto Collision Avoidance System"
                )
                Feature(
                    number = "2",
                    description = "Controlled via wifi"
                )
                Feature(
                    number = "3",
                    description = "Realistic Design"
                )
                Feature(
                    number = "4",
                    description = "Suitable for large scale production"
                )
                Feature(
                    number = "5",
                    description = "Autonomous delivery cars eliminate labor costs associated with drivers and reduce reliance on fuel-powered delivery systems."
                )
                Feature(
                    number = "6",
                    description = "Using electric or hybrid cars reduces greenhouse gas emissions."
                )
                Feature(
                    number = "7",
                    description = "By replacing multiple bikes and vans with fewer, optimized autonomous vehicles, cities can experience less congestion and noise pollution."
                )
                Feature(
                    number = "8",
                    description = "The autonomous delivery system can be scaled to serve diverse industries, including e-commerce, food delivery, and healthcare (e.g., transporting medical supplies)."
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = imageResource(Res.drawable.bg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 375.dp, height = 275.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(40.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Feature(
                        number = "1",
                        description = "ACAS - Auto Collision Avoidance System"
                    )
                    Feature(
                        number = "2",
                        description = "Controlled via wifi"
                    )
                    Feature(
                        number = "3",
                        description = "Realistic Design"
                    )
                    Feature(
                        number = "4",
                        description = "Suitable for large scale production"
                    )
                    Feature(
                        number = "5",
                        description = "Autonomous delivery cars eliminate labor costs associated with drivers and reduce reliance on fuel-powered delivery systems."
                    )
                    Feature(
                        number = "6",
                        description = "Using electric or hybrid cars reduces greenhouse gas emissions."
                    )
                    Feature(
                        number = "7",
                        description = "By replacing multiple bikes and vans with fewer, optimized autonomous vehicles, cities can experience less congestion and noise pollution."
                    )
                    Feature(
                        number = "8",
                        description = "The autonomous delivery system can be scaled to serve diverse industries, including e-commerce, food delivery, and healthcare (e.g., transporting medical supplies)."
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            PreviousButton {
                navigation.navigate("members")
            }
            Spacer(Modifier.width(10.dp))
            HomeButton()
            Spacer(Modifier.width(10.dp))
            NextButton {
                navigation.navigate("map")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}