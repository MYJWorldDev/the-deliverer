package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.Title
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.bg

@Composable
fun HomeScreen() {
    Image(
        bitmap = imageResource(Res.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize().blur(10.dp),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(
            text = "The Deliverer",
            color = Colors.onPrimaryVariant
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "The next stage of technological revolution of deliverability hassle-free.",
            color = Colors.onPrimary,
            fontSize = typography.h6.fontSize,
            style = typography.h6,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontFamily = poppins
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                navigation.navigate("objective")
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
            Text(
                text = "Get Started",
                color = Colors.onPrimary,
                fontSize = typography.h6.fontSize,
                style = typography.h6,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins
            )
            Spacer(Modifier.width(10.dp))
            Image(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp),
                colorFilter = ColorFilter.tint(Colors.onPrimary)
            )
        }
    }
}