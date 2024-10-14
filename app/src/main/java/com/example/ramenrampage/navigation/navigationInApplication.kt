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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ramenrampage.R
import com.example.ramenrampage.ui.screens.ActivityFeedScreen
import com.example.ramenrampage.ui.screens.ActivtyLocationSpotted
import com.example.ramenrampage.ui.screens.AllPhotosOfConsumed
import com.example.ramenrampage.ui.screens.DiscoverScreen
import com.example.ramenrampage.ui.screens.Login_registerScreen
import com.example.ramenrampage.ui.screens.MessageScreen
import com.example.ramenrampage.ui.screens.Profile
import com.example.ramenrampage.ui.screens.RegisterScreen
import com.example.ramenrampage.ui.screens.Search
import com.example.ramenrampage.ui.screens.WelcomeScreen
import com.example.ramenrampage.ui.screens.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationInApplication(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val isBottomBarVisible = remember { mutableStateOf(true) }
    val isTopAppVisible = remember { mutableStateOf(true) }
    val isFloatingActionButtonVisible = remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentSelectedItem = remember { mutableStateOf(AppScreens.Discover) }

    // Check authentication status
    val currentUser = auth.currentUser
    val startDestination = determineStartDestination(auth.currentUser)

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
                AppScreens.Discover.name -> stringResource(R.string.discover)
                AppScreens.Feed.name -> stringResource(R.string.activity)
                AppScreens.LocationSpotted.name -> stringResource(R.string.location_spotted)
                AppScreens.Profile.name -> "It´s You"
                AppScreens.Message.name -> stringResource(R.string.message)
                AppScreens.Login.name -> stringResource(R.string.login)
                AppScreens.Register.name -> stringResource(R.string.register)
                AppScreens.Search.name -> "Noodle hunt"
                AppScreens.Notification.name -> "Whats´s Cooking"
                // TODO:
                //  add the search title or a searchbar
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
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back",
                                tint =  colorResource(id = R.color.blueberry_ble)
                            )
                        }
                    },
                    actions = {
                            if (currentUser != null){
                                IconButton(onClick = {


                                }) {
                                    Icon(
                                     imageVector = Icons.Outlined.Settings,
                                        contentDescription = "User Settings",
                                        tint =  colorResource(id = R.color.blueberry_ble)
                                    )


                                }
                            }
                        else {
                            IconButton(onClick = {
                                navController.navigate(AppScreens.Login.name)
                            }) {
                                Icon(imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "Login",
                                    tint =  colorResource(id = R.color.blueberry_ble))
                            }
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
    }, floatingActionButton = {
        if (isFloatingActionButtonVisible.value){
            SearchFloatingAction(toSearch = {navController.navigate(AppScreens.Search.name)})
        }
    }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {

            composable(AppScreens.Welcome.name) {
                isTopAppVisible.value = false
                isBottomBarVisible.value = false
                isFloatingActionButtonVisible.value = false
                WelcomeScreen(start = {navController.navigate(AppScreens.Discover.name)},
                    loginOrSignUp = {navController.navigate(AppScreens.Login.name)})}

            composable(AppScreens.Discover.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                DiscoverScreen()}

            composable(AppScreens.Feed.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                ActivityFeedScreen()}

            composable(AppScreens.LocationSpotted.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                ActivtyLocationSpotted()}

            composable(AppScreens.Profile.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                Profile(throwOut = {navController.navigate(AppScreens.Welcome.name)},
                    allPics = {navController.navigate(AppScreens.Consumed.name)})}

            composable(AppScreens.Message.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                MessageScreen()}

            composable(AppScreens.Login.name) {
                isBottomBarVisible.value = false
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = false
                Login_registerScreen(
                    takeMeHome = {
                        // checking if user credentials is a match
                        if (currentUser != null) {
                            navController.navigate(AppScreens.Discover.name)
                        } else {
                            navController.navigate(AppScreens.Login.name)
                        } },
                    meMeAUser = {navController.navigate(AppScreens.Register.name)}, auth = auth )
            }

            composable(AppScreens.Register.name) {
                isBottomBarVisible.value = false
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = false
                RegisterScreen(toLogin = {navController.navigate(AppScreens.Login.name)})
            }

            composable(AppScreens.Search.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = false
                Search(takeMeToDiscover = {navController.navigate(AppScreens.Discover.name)})
            }

            composable(AppScreens.Notification.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = false
                Notification()
            }

            composable(AppScreens.Consumed.name) {
                isBottomBarVisible.value = true
                isTopAppVisible.value = true
                isFloatingActionButtonVisible.value = true
                AllPhotosOfConsumed()
            }



        }

        }

        }



//List of Icons and text for navbar(bottom bar)
val items = listOf(

    BottomNavItems(AppScreens.Feed, Icons.Outlined.MoreVert, "Activity"),
    BottomNavItems(AppScreens.Message, Icons.Outlined.MailOutline, "Message"),
    BottomNavItems(AppScreens.Profile, Icons.Outlined.Person, "Profile"),
    BottomNavItems(AppScreens.Notification, Icons.Outlined.Notifications, "Notification")
)


private fun determineStartDestination(currentUser: FirebaseUser?): String {
    return if (currentUser != null) {
        AppScreens.Discover.name
    } else {
        AppScreens.Welcome.name
    }

}

@Composable
fun SearchFloatingAction( toSearch: ()-> Unit) {
    FloatingActionButton(onClick = { toSearch()},
        containerColor = colorResource(id = R.color.blueberry_ble),
        contentColor = Color.White

        ) {
        Icon(imageVector = Icons.Default.Add, contentDescription =" Add Action" )
    }
}













