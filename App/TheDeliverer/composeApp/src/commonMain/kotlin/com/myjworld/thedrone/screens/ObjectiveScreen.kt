package com.myjworld.thedrone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun ObjectiveScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary)
            .verticalScroll(rememberScrollState())
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            text = "Objective",
            color = Colors.onPrimaryVariant
        )
        DecorLine()
        Column(
            modifier = Modifier
                .fillMaxWidth(if (getSize() == "Small") 0.9f else 0.8f)
//                .height(if (getSize() == "Small") 450.dp else 200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Colors.background)
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "“If sending messages online is easy, then delivering physical objects should be too. Autonomous cars equally hold the potential to share physical objects as hassle-free as digital data.”",
                color = Colors.onPrimary,
                fontSize = 24.sp,
                style = typography.h4,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = poppins
            )
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            PreviousButton {
                navigation.navigate("home")
            }
            Spacer(Modifier.width(10.dp))
            HomeButton()
            Spacer(Modifier.width(10.dp))
            NextButton {
                navigation.navigate("members")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}