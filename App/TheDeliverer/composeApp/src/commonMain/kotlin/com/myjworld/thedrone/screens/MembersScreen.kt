package com.myjworld.thedrone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.abd
import thedeliverer.composeapp.generated.resources.faiq
import thedeliverer.composeapp.generated.resources.musab
import thedeliverer.composeapp.generated.resources.myj

@Composable
fun MembersScreen() {
    @Composable
    fun Member(
        name: String,
        role: String,
        image: DrawableResource
    ) {
        Column(
            modifier = Modifier
                .width(375.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Colors.background)
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = imageResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = name,
                color = Colors.onPrimary,
                fontSize = 30.sp,
                style = typography.h4,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = role,
                color = Colors.onPrimaryVariant,
                fontSize = 18.sp,
                style = typography.h4,
                fontWeight = FontWeight.Medium,
                fontFamily = poppins,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
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
            text = "The Team",
            color = Colors.onPrimaryVariant
        )
        DecorLine()
        if (getSize() == "Small") {
            Member(
                name = "M. Abdullah Umair",
                role = "Project Developer, Main coder of microchip",
                image = Res.drawable.abd
            )
            Member(
                name = "M. Musab Khan",
                role = "Assisted in Development, Made the physical hardware",
                image = Res.drawable.musab
            )
            Member(
                name = "M. Yousuf Jamil",
                role = "Android App, Desktop App & Website Developer",
                image = Res.drawable.myj
            )
            Member(
                name = "M. Faaiq",
                role = "Made project body & added aesthetics",
                image = Res.drawable.faiq
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Member(
                    name = "M. Abdullah Umair",
                    role = "Project Developer, Main coder of microchip",
                    image = Res.drawable.abd
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Musab Khan",
                    role = "Assisted in Development, Made the physical hardware",
                    image = Res.drawable.musab
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Yousuf Jamil",
                    role = "Android App, Desktop App & Website Developer",
                    image = Res.drawable.myj
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Faaiq",
                    role = "Made project body & added aesthetics",
                    image = Res.drawable.faiq
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
                navigation.navigate("objective")
            }
            Spacer(Modifier.width(10.dp))
            HomeButton()
            Spacer(Modifier.width(10.dp))
            NextButton {
                navigation.navigate("features")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}