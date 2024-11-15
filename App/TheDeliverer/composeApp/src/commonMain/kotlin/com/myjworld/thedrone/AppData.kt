package com.myjworld.thedrone

import org.jetbrains.compose.resources.DrawableResource
import thedeliverer.composeapp.generated.resources.Res
import thedeliverer.composeapp.generated.resources.myj

object AppData {
    private var imageToShow = Res.drawable.myj

    fun setImage(image: DrawableResource) {
        imageToShow = image
    }

    fun getImage(): DrawableResource {
        return imageToShow
    }
}