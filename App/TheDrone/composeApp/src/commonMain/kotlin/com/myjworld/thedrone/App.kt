package com.myjworld.thedrone

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import thedrone.composeapp.generated.resources.Res
import thedrone.composeapp.generated.resources.bg
import thedrone.composeapp.generated.resources.ic

lateinit var navigation: NavHostController

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    navigation = navController

    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { "Greeting().greet()" }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }

        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { NavigationBar() { coroutineScope.launch { scaffoldState.drawerState.open() } } },
            drawerContent = { NavigationDrawer() { coroutineScope.launch { scaffoldState.drawerState.close() } } },
            bottomBar = { BottomBar() },
            drawerGesturesEnabled = getSize() == "Small" && scaffoldState.drawerState.isOpen
        ) {
            Navigation(navController)
        }

        if (getSize() != "Small" && scaffoldState.drawerState.isOpen) {
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }
}

data class Screen(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)

val screens = listOf(
    Screen("Home", "home", Icons.Sharp.Home) { HomeScreen() },
    Screen("Objective", "objective", Icons.Sharp.Info) { ObjectiveScreen() },
    Screen("Members", "members", Icons.Sharp.Person) { MembersScreen() },
    Screen("Features", "features", Icons.Sharp.Add) { FeaturesScreen() },
    Screen("Gallery", "gallery", Icons.Sharp.Face) { GalleryScreen() },
)

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        screens.forEach { screen ->
            composable(screen.route) {
                screen.screen()
            }
        }
        composable("imageViewer") {
            ImageViewerScreen()
        }
    }
}

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
                text = "The Drone",
                color = Colors.onContainer,
                fontSize = typography.h5.fontSize,
                style = typography.h5,
                fontWeight = FontWeight.Bold
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
                    Spacer(Modifier.width(15.dp))
                }
            }
            Spacer(Modifier.width(15.dp))
        }
    }
}

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
        fontWeight = FontWeight.Bold,
        modifier = Modifier.clickable {
            onClick()
        }
    )
}

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
                text = "The Drone",
                color = Colors.onPrimary,
                fontSize = typography.h4.fontSize,
                style = typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(15.dp))
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
            .padding(vertical = 15.dp, horizontal = 25.dp)
            .clickable {
                onClick()
            }
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
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottomBar() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.container),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "© 2024 The Drone",
                color = Colors.onContainer,
                fontSize = typography.h6.fontSize,
                style = typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun HomeScreen() {
    Image(
        bitmap = imageResource(Res.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize().blur(10.dp),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(Colors.background, blendMode = BlendMode.Overlay)
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "The Drone",
            color = Colors.onPrimary,
            fontSize = typography.h2.fontSize,
            style = typography.h2,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(100.dp))
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
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(10.dp))
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp),
                colorFilter = ColorFilter.tint(Colors.onPrimary)
            )
        }
    }
}

@Composable
fun ObjectiveScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun MembersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun FeaturesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun GalleryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun ImageViewerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}