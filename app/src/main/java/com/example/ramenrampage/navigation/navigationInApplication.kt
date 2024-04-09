package com.example.ramenrampage.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ramenrampage.R
import com.example.ramenrampage.ui.screens.ActivityFeedScreen
import com.example.ramenrampage.ui.screens.ActivtyLocationSpotted
import com.example.ramenrampage.ui.screens.DiscoverScreen
import com.example.ramenrampage.ui.screens.MessageScreen
import com.example.ramenrampage.ui.screens.Profile


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationInApplication() {
    val navController = rememberNavController()
    val isBottomBarVisible = remember { mutableStateOf(true) }
    val isTopAppVisible = remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentSelectedItem = remember { mutableStateOf(AppScreens.Discover) }

    Scaffold(topBar = {
        if (isTopAppVisible.value) {
            // Define the gradient
            val gradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFFA500), // Orange
                    Color(0xFFFFFF00)  // Yellow
                )
            )

            // TopAppBar title based on currentRoute
            val title = when (currentRoute) {
                AppScreens.Discover.name -> "Discover"
                AppScreens.Feed.name -> "Activity"
                AppScreens.LocationSpotted.name -> "Location Spotted"
                AppScreens.Profile.name -> "Profile"
                AppScreens.Message.name -> "Message"
                // Add other screens
                else -> "Ramen Rampage"
            }

            // Apply the gradient as a background to a Box, Column, or any suitable composable
            // that fills the top bar area.
            Column(modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .padding(
                    top = WindowInsets.statusBars
                        .asPaddingValues()
                        .calculateTopPadding()
                )) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent, // Make the AppBar container transparent
                        titleContentColor = colorResource(id = R.color.blueberry_ble)
                    ),
                    title = {
                        Text(
                            title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                        )
                    },
                    navigationIcon = {

                        IconButton(onClick = {
                            // Handle back icon click
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint =  colorResource(id = R.color.blueberry_ble)
                            )
                        }
                    },
                    actions = {

                        IconButton(onClick = {
                            // Handle settings icon click

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint =  colorResource(id = R.color.blueberry_ble)
                            )
                        }
                    },
                    windowInsets = WindowInsets.statusBars.only(WindowInsetsSides.Top)
                )
            }
        }
    }, bottomBar = {
        if (isBottomBarVisible.value) {
            BottomAppBar(containerColor = colorResource(R.color.orange_500)) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 20.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (item in items) {
                        val isSelected = item.screen == currentSelectedItem.value
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(item.screen.name)
                                    currentSelectedItem.value = item.screen
                                }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(30.dp),
                                tint = if (isSelected) colorResource(id = R.color.white) else colorResource(
                                    id = R.color.blueberry_ble
                                )
                            )
                            Text(
                                text = item.label,
                                color = if (isSelected) colorResource(id = R.color.white) else colorResource(
                                    id = R.color.blueberry_ble
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Discover.name,
            Modifier.padding(innerPadding)
        ) {

            composable(AppScreens.Discover.name) { DiscoverScreen()}

            composable(AppScreens.Feed.name) { ActivityFeedScreen()}

            composable(AppScreens.LocationSpotted.name) { ActivtyLocationSpotted()}

            composable(AppScreens.Profile.name) { Profile()}

            composable(AppScreens.Message.name) { MessageScreen()}

        }

        }

        }

//List of Icons and text for navbar(bottom bar)
val items = listOf(

    BottomNavItems(AppScreens.Feed, Icons.Outlined.MoreVert, "Activity"),
    BottomNavItems(AppScreens.Message, Icons.Outlined.MailOutline, "Message"),
    BottomNavItems(AppScreens.Profile, Icons.Outlined.Person, "Profile")
)











