package com.example.ramenrampage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ramenrampage.R



@Composable
fun WelcomeScreen(start: () -> Unit, loginOrSignUp: ()-> Unit) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFA500),  // Orange
            Color(0xFFFFFF00)   // Yellow
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpaceEm(amount = 150)

            ImagePlacer(painterResource(id = R.drawable.untitled_artwork),
                contentDesciption = "Logo", start)

            SpaceEm(amount = 50)

            TextAndClick(loginOrSignUp = {
                loginOrSignUp()
            },
                text = "Login or SignUp" )
            
            SpaceEm(amount = 10)

            TextAndClick(loginOrSignUp = {
                start()
            },
                text =  "Preview App")


        }
    }
}

@Composable
fun ImagePlacer(painter: Painter, contentDesciption: String, start : () -> Unit ) {
    Image(painter = painter,
        contentDescription = contentDesciption,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { start() })
}

@Composable
fun SpaceEm(amount: Int) {
    Spacer(modifier = Modifier.height(amount.dp))
}

@Composable
fun TextAndClick(loginOrSignUp : () -> Unit, text: String) {
    Text(
        modifier = Modifier.clickable {
            loginOrSignUp()
        },
        text = text)
}



