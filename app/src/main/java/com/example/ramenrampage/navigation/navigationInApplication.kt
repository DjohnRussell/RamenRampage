package com.example.ramenrampage.navigation

import android.content.res.Resources.Theme
import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
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
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    titleContentColor = Color.White

                ),
                title = {
                    Text(
                        "Ramen Rampage",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Handle settings icon click

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }

                }
            )
        }
    }, bottomBar = {
        if (isBottomBarVisible.value) {
            BottomAppBar(containerColor = colorResource(R.color.purple_500)) {
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
                                tint = if (isSelected) colorResource(id = R.color.black) else Color.White
                            )
                            Text(
                                text = item.label,
                                color = if (isSelected) colorResource(id = R.color.black) else Color.White
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

val items = listOf(

    BottomNavItems(AppScreens.Feed, Icons.Default.MoreVert, "Activity"),
    BottomNavItems(AppScreens.Message, Icons.Default.MailOutline, "Message"),
    BottomNavItems(AppScreens.Profile, Icons.Default.Person, "Profile")
)











