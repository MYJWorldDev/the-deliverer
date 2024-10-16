package com.myjworld.thedrone

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import thedrone.composeapp.generated.resources.Res
import thedrone.composeapp.generated.resources.compose_multiplatform
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
            topBar = { NavigationBar() },
            drawerContent = { NavigationDrawer() },
            bottomBar = { BottomBar() },
            drawerGesturesEnabled = getSize() != "Small"
        ) {
            Navigation(navController)
        }
    }
}

data class Screen(
    val title: String,
    val route: String,
    val screen: @Composable () -> Unit
)

val screens = listOf(
    Screen("Home", "home") { HomeScreen() },
    Screen("About", "about") { AboutScreen() }
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
    }
}

@Composable
fun NavigationBar() {
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
                color = Colors.onPrimary,
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
                            navigation.navigate("home")
                        }
                )
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
fun NavigationDrawer() {

}

@Composable
fun NavigationBarItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit = {}
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
fun BottomBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { }
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}