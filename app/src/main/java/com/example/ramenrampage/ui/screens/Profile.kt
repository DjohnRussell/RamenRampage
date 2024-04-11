package com.example.ramenrampage.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ramenrampage.ui.screens.viewModels.FirebaseViewModel
import com.example.ramenrampage.R

@Composable
fun Profile(throwOut: () -> Unit) {
    val firebaseViewModel: FirebaseViewModel = viewModel()

    TextAndClick(loginOrSignUp = {
        firebaseViewModel.logOut()
        throwOut()
        },
        text = "Logout",
        colorText = colorResource(id = R.color.blueberry_ble))



}