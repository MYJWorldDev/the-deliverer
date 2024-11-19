package com.myjworld.thedrone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.myjworld.thedrone.Fonts.poppins
import com.myjworld.thedrone.mainnavigation.BottomBar
import com.myjworld.thedrone.mainnavigation.NavigationBar
import com.myjworld.thedrone.mainnavigation.NavigationDrawer
import com.myjworld.thedrone.mainnavigation.Screens.Navigation
import com.myjworld.thedrone.mainnavigation.navigator.initializeNavigation
import com.myjworld.thedrone.mainnavigation.navigator.navigation
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

// Central Function

@Composable
@Preview
fun App() {

//    Initialize Navigation

    val navController = rememberNavController()
    initializeNavigation(navController)

//    Main Screen

    MaterialTheme {
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

// Utilities

@Composable
fun Title(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        fontSize = typography.h2.fontSize,
        style = typography.h2,
        fontWeight = FontWeight.SemiBold,
        fontFamily = poppins,
        textAlign = TextAlign.Center
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