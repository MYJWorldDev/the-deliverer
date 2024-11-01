package com.myjworld.thedrone

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import thedrone.composeapp.generated.resources.Res
import thedrone.composeapp.generated.resources.abd
import thedrone.composeapp.generated.resources.bg
import thedrone.composeapp.generated.resources.faiq
import thedrone.composeapp.generated.resources.ic
import thedrone.composeapp.generated.resources.musab
import thedrone.composeapp.generated.resources.myj

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
//    Screen("Themes", "themes", Icons.Default.Settings) { ThemesScreen() }
)

data class Palette(
    val name: String,
    val primary: Color,
    val onPrimary: Color,
    val onPrimaryContrast: Color,
    val primaryVariant: Color,
    val onPrimaryVariant: Color,
    val container: Color,
    val onContainer: Color,
    val onContainerVariant: Color,
    val background: Color
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
//            Image(
//                imageVector = Icons.Default.KeyboardArrowDown,
//                contentDescription = "Download App",
//                modifier = Modifier,
//                colorFilter = ColorFilter.tint(Colors.onContainerVariant)
//            )
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
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottomBar() {
    Column(
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
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(
            text = "The Drone",
            color = Colors.onPrimaryVariant
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "The next stage of technological revolution of deliverability hassle-free.",
            color = Colors.onPrimary,
            fontSize = typography.h6.fontSize,
            style = typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
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
                fontWeight = FontWeight.Bold
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
                .height(if (getSize() == "Small") 450.dp else 200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Colors.background)
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "“If sending messages online is easy, then delivering physical objects should be too. Drones equally hold the potential to share physical objects as hassle-free as digital data.”",
                color = Colors.onPrimary,
                fontSize = 24.sp,
                style = typography.h4,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
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
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = role,
                color = Colors.onPrimaryVariant,
                fontSize = 18.sp,
                style = typography.h4,
                fontWeight = FontWeight.Bold,
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
            text = "Members",
            color = Colors.onPrimaryVariant
        )
        DecorLine()
        if (getSize() == "Small") {
            Member(
                name = "M. Abdullah Umair",
                role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                image = Res.drawable.abd
            )
            Member(
                name = "M. Musab Khan",
                role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                image = Res.drawable.musab
            )
            Member(
                name = "M. Yousuf Jamil",
                role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                image = Res.drawable.myj
            )
            Member(
                name = "M. Faaiq",
                role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                image = Res.drawable.faiq
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Member(
                    name = "M. Abdullah Umair",
                    role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                    image = Res.drawable.abd
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Musab Khan",
                    role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                    image = Res.drawable.musab
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Yousuf Jamil",
                    role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
                    image = Res.drawable.myj
                )
                Spacer(modifier = Modifier.width(10.dp))
                Member(
                    name = "M. Faaiq",
                    role = "Lorem ipsum odor amet, consectetuer adipiscing elit. Faucibus urna conubia; fusce faucibus ante arcu litora fringilla.",
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
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(Colors.primaryVariant)
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = description,
                        color = Colors.onPrimary,
                        fontSize = 18.sp,
                        style = typography.h4
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
                    description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                )
                Feature(
                    number = "2",
                    description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                )
                Feature(
                    number = "3",
                    description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                )
                Feature(
                    number = "4",
                    description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
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
                        description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                    )
                    Feature(
                        number = "2",
                        description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                    )
                    Feature(
                        number = "3",
                        description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
                    )
                    Feature(
                        number = "4",
                        description = "Lorem ipsum odor amet, consectetuer adipiscing elit. Tortor turpis interdum semper cras erat eleifend suscipit."
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
                navigation.navigate("gallery")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

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
                    fontWeight = FontWeight.Bold
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
                image = Res.drawable.bg
            )
            ShowImage(
                image = Res.drawable.bg
            )
            ShowImage(
                image = Res.drawable.bg
            )
            ShowImage(
                image = Res.drawable.bg
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShowImage(
                    image = Res.drawable.bg
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.bg
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.bg
                )
                Spacer(Modifier.width(10.dp))
                ShowImage(
                    image = Res.drawable.bg
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
                navigation.navigate("features")
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

@Composable
fun ImageViewerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.Start
    ) {
        val imageToShow = AppData.getImage()
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Spacer(Modifier.width(15.dp))
            PreviousButton {
                navigation.navigate("gallery")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            bitmap = imageResource(imageToShow),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun ThemesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Spacer(Modifier.width(15.dp))
            PreviousButton {
                navigation.navigate("home")
            }
        }

        @Composable
        fun Theme(
            name: String,
            primary: Color,
            onPrimary: Color,
            onPrimaryContrast: Color,
            primaryVariant: Color,
            onPrimaryVariant: Color,
            container: Color,
            onContainer: Color,
            onContainerVariant: Color,
            background: Color
        ) {
            var showMsg by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .width(375.dp)
                    .height(260.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(primary)
                    .clickable {
                        Colors.primary = primary
                        Colors.onPrimary = onPrimary
                        Colors.onPrimaryContrast = onPrimaryContrast
                        Colors.primaryVariant = primaryVariant
                        Colors.onPrimaryVariant = onPrimaryVariant
                        Colors.container = container
                        Colors.onContainer = onContainer
                        Colors.onContainerVariant = onContainerVariant
                        Colors.background = background

                        showMsg = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    color = onPrimary,
                    fontSize = 30.sp,
                    style = typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }

            if (showMsg) {
                AlertDialog(
                    onDismissRequest = { showMsg = false },
                    title = {
                        Text(
                            text = "Theme Changed.",
                            color = onPrimary,
                            fontSize = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    buttons = {
                        Button(
                            onClick = { showMsg = false },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = primary,
                                contentColor = onPrimary
                            )
                        ) {
                            Text(
                                text = "OK",
                                color = onPrimary,
                                fontSize = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                )
                showMsg = false
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        val themes = listOf(
            Palette(
                name = "Default",
                primary = Color(0xFF394379),
                onPrimary = Color(0xFFdee0ff),
                onPrimaryContrast = Color(0xFF11184a),
                primaryVariant = Color(0xFF515b92),
                onPrimaryVariant = Color(0xFFffffff),
                container = Color(0xFF202c61),
                onContainer = Color(0xFFb9c3ff),
                onContainerVariant = Color(0xFFffb4ab),
                background = Color(0xFF000614)
            ),
            Palette(
                name = "Red",
                primary = Color(0xFFFF3E5B),
                onPrimary = Color(0xFFFFFFFF),
                onPrimaryContrast = Color(0xFF000000),
                primaryVariant = Color(0xFF9A0036),
                onPrimaryVariant = Color(0xFFFFFFFF),
                container = Color(0xFFE8B7B9),
                onContainer = Color(0xFF000000),
                onContainerVariant = Color(0xFF202020),
                background = Color(0xFFFFFFFF)
            ),
            Palette(
                name = "Teal",
                primary = Color(0xFF3E9A8A),
                onPrimary = Color(0xFFFFFFFF),
                onPrimaryContrast = Color(0xFF000000),
                primaryVariant = Color(0xFF006767),
                onPrimaryVariant = Color(0xFFFFFFFF),
                container = Color(0xFFB7D4D4),
                onContainer = Color(0xFF000000),
                onContainerVariant = Color(0xFF202020),
                background = Color(0xFFFFFFFF)
            ),
            Palette(
                name = "Orange",
                primary = Color(0xFFFF9A00),
                onPrimary = Color(0xFFFFFFFF),
                onPrimaryContrast = Color(0xFF000000),
                primaryVariant = Color(0xFF9A6700),
                onPrimaryVariant = Color(0xFFFFFFFF),
                container = Color(0xFFE8D4B7),
                onContainer = Color(0xFF000000),
                onContainerVariant = Color(0xFF202020),
                background = Color(0xFFFFFFFF)
            )
        )

        if (getSize() == "Small") {
            themes.forEach { theme ->
                Theme(
                    name = theme.name,
                    primary = theme.primary,
                    onPrimary = theme.onPrimary,
                    onPrimaryContrast = theme.onPrimaryContrast,
                    primaryVariant = theme.primaryVariant,
                    onPrimaryVariant = theme.onPrimaryVariant,
                    container = theme.container,
                    onContainer = theme.onContainer,
                    onContainerVariant = theme.onContainerVariant,
                    background = theme.background
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                themes.forEach { theme ->
                    Theme(
                        name = theme.name,
                        primary = theme.primary,
                        onPrimary = theme.onPrimary,
                        onPrimaryContrast = theme.onPrimaryContrast,
                        primaryVariant = theme.primaryVariant,
                        onPrimaryVariant = theme.onPrimaryVariant,
                        container = theme.container,
                        onContainer = theme.onContainer,
                        onContainerVariant = theme.onContainerVariant,
                        background = theme.background
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun Title(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = typography.h2.fontSize,
        style = typography.h2,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DecorLine() {
    Spacer(Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Colors.onPrimaryContrast)
    )
    Spacer(Modifier.height(30.dp))
}

@Composable
fun NextButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.container,
            contentColor = Colors.onContainer
        ),
        modifier = Modifier
            .width(56.dp)
            .clip(RoundedCornerShape(100.dp))
    ) {
        Spacer(Modifier.height(40.dp))
        Image(
            imageVector = Icons.AutoMirrored.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            colorFilter = ColorFilter.tint(Colors.onContainer)
        )
    }
}

@Composable
fun PreviousButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.container,
            contentColor = Colors.onContainer
        ),
        modifier = Modifier
            .width(56.dp)
            .clip(RoundedCornerShape(100.dp))
    ) {
        Spacer(Modifier.height(40.dp))
        Image(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            colorFilter = ColorFilter.tint(Colors.onContainer)
        )
    }
}

@Composable
fun HomeButton() {
    Button(
        onClick = {
            navigation.navigate("home")
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.container,
            contentColor = Colors.onContainer
        ),
        modifier = Modifier
            .width(56.dp)
            .clip(RoundedCornerShape(100.dp))
    ) {
        Spacer(Modifier.height(40.dp))
        Image(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp),
            colorFilter = ColorFilter.tint(Colors.onContainer)
        )
    }
}