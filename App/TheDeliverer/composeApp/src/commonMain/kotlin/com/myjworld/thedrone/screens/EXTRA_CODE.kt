package com.myjworld.thedrone.screens

//@Composable
//fun ThemesScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Colors.background)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Spacer(modifier = Modifier.height(20.dp))
//        Row {
//            Spacer(Modifier.width(15.dp))
//            PreviousButton {
//                navigation.navigate("home")
//            }
//        }
//
//        @Composable
//        fun Theme(
//            name: String,
//            primary: Color,
//            onPrimary: Color,
//            onPrimaryContrast: Color,
//            primaryVariant: Color,
//            onPrimaryVariant: Color,
//            container: Color,
//            onContainer: Color,
//            onContainerVariant: Color,
//            background: Color
//        ) {
//            var showMsg by remember { mutableStateOf(false) }
//            Box(
//                modifier = Modifier
//                    .width(375.dp)
//                    .height(260.dp)
//                    .clip(RoundedCornerShape(50.dp))
//                    .background(primary)
//                    .clickable {
//                        Colors.primary = primary
//                        Colors.onPrimary = onPrimary
//                        Colors.onPrimaryContrast = onPrimaryContrast
//                        Colors.primaryVariant = primaryVariant
//                        Colors.onPrimaryVariant = onPrimaryVariant
//                        Colors.container = container
//                        Colors.onContainer = onContainer
//                        Colors.onContainerVariant = onContainerVariant
//                        Colors.background = background
//
//                        showMsg = true
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = name,
//                    color = onPrimary,
//                    fontSize = 30.sp,
//                    style = typography.h4,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            if (showMsg) {
//                AlertDialog(
//                    onDismissRequest = { showMsg = false },
//                    title = {
//                        Text(
//                            text = "Theme Changed.",
//                            color = onPrimary,
//                            fontSize = 24.sp,
//                            style = typography.h4,
//                            fontWeight = FontWeight.Bold
//                        )
//                    },
//                    buttons = {
//                        Button(
//                            onClick = { showMsg = false },
//                            colors = ButtonDefaults.buttonColors(
//                                backgroundColor = primary,
//                                contentColor = onPrimary
//                            )
//                        ) {
//                            Text(
//                                text = "OK",
//                                color = onPrimary,
//                                fontSize = 24.sp,
//                                style = typography.h4,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//                )
//                showMsg = false
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//
//        val themes = listOf(
//            Palette(
//                name = "Default",
//                primary = Color(0xFF394379),
//                onPrimary = Color(0xFFdee0ff),
//                onPrimaryContrast = Color(0xFF11184a),
//                primaryVariant = Color(0xFF515b92),
//                onPrimaryVariant = Color(0xFFffffff),
//                container = Color(0xFF202c61),
//                onContainer = Color(0xFFb9c3ff),
//                onContainerVariant = Color(0xFFffb4ab),
//                background = Color(0xFF000614)
//            ),
//            Palette(
//                name = "Red",
//                primary = Color(0xFFFF3E5B),
//                onPrimary = Color(0xFFFFFFFF),
//                onPrimaryContrast = Color(0xFF000000),
//                primaryVariant = Color(0xFF9A0036),
//                onPrimaryVariant = Color(0xFFFFFFFF),
//                container = Color(0xFFE8B7B9),
//                onContainer = Color(0xFF000000),
//                onContainerVariant = Color(0xFF202020),
//                background = Color(0xFFFFFFFF)
//            ),
//            Palette(
//                name = "Teal",
//                primary = Color(0xFF3E9A8A),
//                onPrimary = Color(0xFFFFFFFF),
//                onPrimaryContrast = Color(0xFF000000),
//                primaryVariant = Color(0xFF006767),
//                onPrimaryVariant = Color(0xFFFFFFFF),
//                container = Color(0xFFB7D4D4),
//                onContainer = Color(0xFF000000),
//                onContainerVariant = Color(0xFF202020),
//                background = Color(0xFFFFFFFF)
//            ),
//            Palette(
//                name = "Orange",
//                primary = Color(0xFFFF9A00),
//                onPrimary = Color(0xFFFFFFFF),
//                onPrimaryContrast = Color(0xFF000000),
//                primaryVariant = Color(0xFF9A6700),
//                onPrimaryVariant = Color(0xFFFFFFFF),
//                container = Color(0xFFE8D4B7),
//                onContainer = Color(0xFF000000),
//                onContainerVariant = Color(0xFF202020),
//                background = Color(0xFFFFFFFF)
//            )
//        )
//
//        if (getSize() == "Small") {
//            themes.forEach { theme ->
//                Theme(
//                    name = theme.name,
//                    primary = theme.primary,
//                    onPrimary = theme.onPrimary,
//                    onPrimaryContrast = theme.onPrimaryContrast,
//                    primaryVariant = theme.primaryVariant,
//                    onPrimaryVariant = theme.onPrimaryVariant,
//                    container = theme.container,
//                    onContainer = theme.onContainer,
//                    onContainerVariant = theme.onContainerVariant,
//                    background = theme.background
//                )
//            }
//        }
//        else {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                themes.forEach { theme ->
//                    Theme(
//                        name = theme.name,
//                        primary = theme.primary,
//                        onPrimary = theme.onPrimary,
//                        onPrimaryContrast = theme.onPrimaryContrast,
//                        primaryVariant = theme.primaryVariant,
//                        onPrimaryVariant = theme.onPrimaryVariant,
//                        container = theme.container,
//                        onContainer = theme.onContainer,
//                        onContainerVariant = theme.onContainerVariant,
//                        background = theme.background
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(50.dp))
//    }
//}

//data class Palette(
//    val name: String,
//    val primary: Color,
//    val onPrimary: Color,
//    val onPrimaryContrast: Color,
//    val primaryVariant: Color,
//    val onPrimaryVariant: Color,
//    val container: Color,
//    val onContainer: Color,
//    val onContainerVariant: Color,
//    val background: Color
//)