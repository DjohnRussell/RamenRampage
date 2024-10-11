package com.example.ramenrampage.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ramenrampage.R
import com.example.ramenrampage.ui.screens.viewModels.FirebaseViewModel

@Composable
fun RegisterScreen(toLogin: ()-> Unit) {
    val firebaseViewModel: FirebaseViewModel = viewModel()
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualTransformation = remember {
        PasswordVisualTransformation()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {


        OutlinedTextField(value = firebaseViewModel.username.value,
            onValueChange =  {newValue -> firebaseViewModel.username.value = newValue},
            label = { Text(text = "Username") },
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )


        SpaceEm(16)

        OutlinedTextField(value = firebaseViewModel.email.value,
            onValueChange =  {newValue -> firebaseViewModel.email.value = newValue},
            label = { Text(text = stringResource(id = R.string.email)) },
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble))
        )

        SpaceEm(16)

       

        OutlinedTextField(value = firebaseViewModel.password.value ,
            onValueChange =  {newValue -> firebaseViewModel.password.value = newValue},
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                passwordVisualTransformation
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = colorResource(id = R.color.blueberry_ble)),
            modifier = Modifier,
            trailingIcon = {
                Icon(
                    if (showPassword) {
                        Icons.Filled.Visibility
                    }else{
                        Icons.Filled.VisibilityOff
                    }, contentDescription = "Toddle password visibility",
                    modifier = Modifier.clickable { showPassword = !showPassword }
                )
            }
        )

        SpaceEm(40)

        Button(onClick = { toLogin();
            firebaseViewModel.registerUser()},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id =
                R.color.blueberry_ble)
            )
        ) {
            Text(text = stringResource(R.string.sign_up))
        }




}
}

