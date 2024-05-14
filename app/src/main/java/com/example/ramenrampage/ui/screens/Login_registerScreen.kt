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
import androidx.compose.ui.res.stringResource
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
            label = { Text(text = stringResource(R.string.email)) },
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )

        SpaceEm(16)

        OutlinedTextField(value = firebaseViewModel.password.value ,
            onValueChange =  {newValue -> firebaseViewModel.password.value = newValue},
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )

        SpaceEm(40)


        ButtonWithToast(firebaseViewModel, { takeMeHome() },
            stringResource(R.string.sign_in),auth = auth,
            stringResource(R.string.incorrect_email_or_password_please_try_again) )

        SpaceEm(amount = 10)

        TextAndClick(loginOrSignUp = { meMeAUser() },
            text = stringResource(R.string.register_now),
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
            text = stringResource(R.string.forgot_password),
            toastMessage = stringResource(R.string.password_reset_link_sent_to_provided_email),
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
                        Toast.makeText(context,
                            context.getString(
                                R.string.failed_to_send_reset_email,
                                e.localizedMessage
                            ), Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context,
                    context.getString(R.string.please_enter_your_email_address_before_resetting_your_password), Toast.LENGTH_SHORT).show()
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





