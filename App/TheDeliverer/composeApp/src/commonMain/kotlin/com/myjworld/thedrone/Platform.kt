package com.myjworld.thedrone

import androidx.compose.runtime.Composable

// Get multiplatform platform name
expect fun getPlatform(): String

// Get Size of device
@Composable
expect fun getSize(): String

// Know if phone is android or not
@Composable
expect fun phoneAnroid(): Boolean