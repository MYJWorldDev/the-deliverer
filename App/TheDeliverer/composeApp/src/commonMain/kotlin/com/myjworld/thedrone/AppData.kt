package com.myjworld.thedrone

import org.jetbrains.compose.resources.DrawableResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.myj

// Data for image viewer singleton

object AppData {
    private var imageToShow = Res.drawable.myj
    private var returnRoute = "home"

    fun setImage(image: DrawableResource) {
        imageToShow = image
    }

    fun getImage(): DrawableResource {
        return imageToShow
    }

    fun setReturnRoute(route: String) {
        returnRoute = route
    }

    fun getReturnRoute(): String {
        return returnRoute
    }
}