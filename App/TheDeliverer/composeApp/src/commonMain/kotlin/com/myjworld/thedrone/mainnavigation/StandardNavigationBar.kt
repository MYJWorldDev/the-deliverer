package com.myjworld.thedrone.mainnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myjworld.thedrone.Colors
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.downloadApp
import com.myjworld.thedrone.getPlatform
import com.myjworld.thedrone.getSize
import com.myjworld.thedrone.mainnavigation.Screens.screens
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import com.myjworld.thedrone.phoneAnroid
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.download
import thedeliverer.composeapp.generated.resources.ic

// Standard  navigation bar

@Composable
fun NavigationBar(
    onMobileNavClick: () -> Unit
) {
    var refresher by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    fun refresh() {
        coroutineScope.launch {
            delay(1000)
            if (refresher <= 10) refresher++ else refresher = 0
        }
    }

    refresh()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Colors.container)
            .padding((15 + refresher - refresher).dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
                .clip(RoundedCornerShape(25.dp))
                .clickable {
                    navigation.navigate("home")
                }
        ) {
            Image(
                bitmap = imageResource(Res.drawable.ic),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(100.dp))
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "The Deliverer",
                color = Colors.onContainer,
                fontSize = typography.h5.fontSize,
                style = typography.h5,
                fontWeight = FontWeight.Medium,
                fontFamily = poppins
            )
        }
        Row {
            if (getSize() == "Small") {
                Image(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onMobileNavClick()
                        },
                    colorFilter = ColorFilter.tint(Colors.onContainer)
                )
                Spacer(Modifier.width(15.dp))
            } else {
                screens.forEach { screen ->
                    NavigationBarItem(
                        label = screen.title,
                        selected = navigation.currentDestination?.route == screen.route,
                        onClick = {
                            navigation.navigate(screen.route)
                        }
                    )
                    Spacer(Modifier.width(30.dp))
                }
            }
            if (getPlatform() == "Web with Kotlin/Wasm") {
                val androidPhone = phoneAnroid()
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Colors.primary)
                        .border(2.dp, Colors.onPrimary, RoundedCornerShape(100.dp))
                        .clickable {
                            if (androidPhone) {
                                downloadApp("https://play.google.com/store/apps/details?id=com.myjworld.thedrone")
                            } else {
                                downloadApp("https://myj-world.github.io/the-deliverer/download.html")
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.download),
                        contentDescription = "Download App",
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.5f),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = ColorFilter.tint(Colors.onPrimary)
                    )
                }
            }
            Spacer(Modifier.width(15.dp))
        }
    }
}

// Single item in navigation bar

@Composable
fun NavigationBarItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        color = if (selected) Colors.onContainerVariant else Colors.onContainer,
        fontSize = typography.h5.fontSize,
        style = typography.h5,
        fontWeight = FontWeight.Normal,
        fontFamily = poppins,
        modifier = Modifier.clickable {
            onClick()
        }
    )
}