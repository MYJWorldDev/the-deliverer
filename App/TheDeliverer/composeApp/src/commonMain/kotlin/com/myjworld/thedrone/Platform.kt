package com.myjworld.thedrone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform