package com.myjworld.thedrone

import androidx.compose.runtime.Composable

expect fun getPlatform(): String

@Composable
expect fun getSize(): String