package com.example.ramenrampage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ramenrampage.R

import com.example.ramenrampage.ui.screens.viewModels.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.time.delay




@Composable
fun Login_registerScreen(takeMeHome: ()-> Unit, meMeAUser: ()-> Unit, auth: FirebaseAuth ) {
    val firebaseViewModel: FirebaseViewModel = viewModel()




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {




        SpaceEm(16)

        OutlinedTextField(value = firebaseViewModel.email.value,
            onValueChange =  {newValue -> firebaseViewModel.email.value = newValue},
            label = { Text(text = "Email") },
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )

        SpaceEm(16)

        OutlinedTextField(value = firebaseViewModel.password.value ,
            onValueChange =  {newValue -> firebaseViewModel.password.value = newValue},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )

        SpaceEm(40)

     //  Button(onClick = {
     //      firebaseViewModel.loginUser()

     //      takeMeHome()},
     //      colors = ButtonDefaults.buttonColors(
     //          containerColor = colorResource(id =
     //          R.color.blueberry_ble)
     //      )
     //  ) {
     //      Text(text = "Sign in")
     //  }

        ButtonWithToast(firebaseViewModel, { takeMeHome() }, "Sign in",auth = auth,
            "Failed to sign in" )

        SpaceEm(amount = 10)

        TextAndClick(loginOrSignUp = { meMeAUser() },
            text = "Register NOW",
            colorText = colorResource(id = R.color.blueberry_ble))

        SpaceEm(30)

        ClickableTextWithToast(text = "Forgot password ? ",
            toastMessage = "Reset link sent to provided email \uD83D\uDCE9")

    }
}


//---------------------------------------

@Composable
fun ButtonWithToast(
    firebaseViewModel: FirebaseViewModel,
    takeMeHome: () -> Unit,
    buttonText: String,
    auth: FirebaseAuth,
    toastMessage: String
) {
    val currentUser = auth.currentUser
    val context = LocalContext.current

    Button(onClick = {
        firebaseViewModel.loginUser()
        takeMeHome()

        if (currentUser == null) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = buttonText)
    }
}

@Composable
fun ClickableTextWithToast(text: String, toastMessage: String) {
    val context = LocalContext.current
    Text(
        text = text,
        color = Color.Red,
        modifier = Modifier.clickable {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

        }
    )
}

@Composable
fun ToastToShow(toastMessage: String) {
    val context = LocalContext.current
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
}





