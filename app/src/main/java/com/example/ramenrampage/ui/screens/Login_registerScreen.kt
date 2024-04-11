package com.example.ramenrampage.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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


        ButtonWithToast(firebaseViewModel, { takeMeHome() }, "Sign in",auth = auth,
            "Incorrect email or password. Please try again‼\uFE0F" )

        SpaceEm(amount = 10)

        TextAndClick(loginOrSignUp = { meMeAUser() },
            text = "Register NOW",
            colorText = colorResource(id = R.color.blueberry_ble))

        SpaceEm(30)

        // Calculate the email to send
        val emailToSend = if (firebaseViewModel.email.value.isNotBlank()) {
            firebaseViewModel.email.value
        } else {
            null // Use null to represent no valid email, and handle it inside ClickableTextWithToast
        }

        // Pass the calculated email or null to the Composable
        ClickableTextWithToast(
            text = "Forgot password ?",
            toastMessage = "Password reset link sent to provided email \uD83D\uDCE9",
            auth = FirebaseAuth.getInstance(),
            email = emailToSend
        )
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
fun ClickableTextWithToast(
    text: String,
    toastMessage: String,
    auth: FirebaseAuth,
    email: String? // Accept null to handle no email provided
) {
    val context = LocalContext.current

    Text(
        text = text,
        color = Color.Red,
        modifier = Modifier.clickable {
            if (email != null) {
                auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Failed to send reset email: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Please enter your email address before resetting your password", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
fun TextToster(text: String, message: String) {
    val context = LocalContext.current
    Text(text = text,
        color = Color.Red,
        modifier = Modifier.clickable {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })
}

@Composable
fun ToastToShow(toastMessage: String) {
    val context = LocalContext.current
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
}





