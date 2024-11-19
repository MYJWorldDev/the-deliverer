package com.myjworld.thedrone.mainnavigation

import androidx.navigation.NavHostController

object navigator {

//    Central navigator to be used throughout app

    lateinit var navigation: NavHostController
    fun initializeNavigation(navController: NavHostController) {
        navigation = navController
    }
}