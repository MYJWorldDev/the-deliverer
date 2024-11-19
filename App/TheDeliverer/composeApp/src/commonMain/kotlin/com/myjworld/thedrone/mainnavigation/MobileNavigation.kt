package com.myjworld.thedrone.mainnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.mainnavigation.Screens.screens
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import org.jetbrains.compose.resources.imageResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.ic

// Main top navigation drawer for movile

@Composable
fun NavigationDrawer(
    closeDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.primary)
            .padding(vertical = 35.dp, horizontal = 15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                bitmap = imageResource(Res.drawable.ic),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = "The Deliverer",
                color = Colors.onPrimary,
                fontSize = typography.h4.fontSize,
                style = typography.h4,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins
            )
        }
        Spacer(Modifier.height(60.dp))
        screens.forEach { screen ->
            NavigationDrawerItem(
                label = screen.title,
                icon = screen.icon,
                onClick = {
                    navigation.navigate(screen.route)
                    closeDrawer()
                }
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

// Item to show in navigation drawer

@Composable
fun NavigationDrawerItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(Colors.primaryVariant)
            .clickable {
                onClick()
            }
            .padding(vertical = 15.dp, horizontal = 25.dp)
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            colorFilter = ColorFilter.tint(Colors.onPrimaryVariant)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = label,
            color = Colors.onPrimaryVariant,
            fontSize = typography.h6.fontSize,
            style = typography.h6,
            fontWeight = FontWeight.Normal,
            fontFamily = poppins
        )
    }
}
