package com.myjworld.thedrone.mainnavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myjworld.thedrone.screens.FeaturesScreen
import com.myjworld.thedrone.screens.GalleryScreen
import com.myjworld.thedrone.screens.HomeScreen
import com.myjworld.thedrone.screens.ImageViewerScreen
import com.myjworld.thedrone.screens.MapScreen
import com.myjworld.thedrone.screens.MembersScreen
import com.myjworld.thedrone.screens.ObjectiveScreen
import com.myjworld.thedrone.screens.ProspectsScreen

object Screens {

//    Single screen data class

    data class Screen(
        val title: String,
        val route: String,
        val icon: ImageVector,
        val screen: @Composable () -> Unit
    )

//    List of screens to show in navigation bar

    val screens = listOf(
        Screen("Home", "home", Icons.Sharp.Home) { HomeScreen() },
        Screen("Objective", "objective", Icons.Sharp.Info) { ObjectiveScreen() },
        Screen("Members", "members", Icons.Sharp.Person) { MembersScreen() },
        Screen("Features", "features", Icons.Sharp.Add) { FeaturesScreen() },
        Screen("Map", "map", Icons.Default.LocationOn) { MapScreen() },
        Screen("Prospects", "prospects", Icons.AutoMirrored.Filled.KeyboardArrowRight) { ProspectsScreen() },
        Screen("Gallery", "gallery", Icons.Sharp.Face) { GalleryScreen() },
//    Screen("Themes", "themes", Icons.Default.Settings) { ThemesScreen() }
    )

//    Main navigator throughout app

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
}