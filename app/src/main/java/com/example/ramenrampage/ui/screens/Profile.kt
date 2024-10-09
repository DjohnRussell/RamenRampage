package com.example.ramenrampage.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.ramenrampage.ui.screens.viewModels.FirebaseViewModel
import com.example.ramenrampage.R
import com.example.ramenrampage.models.loadImage
import com.example.ramenrampage.models.loadImageAndClick
import com.example.ramenrampage.models.pathToImages

@Composable
fun Profile(throwOut: () -> Unit) {
    val firebaseViewModel: FirebaseViewModel = viewModel()

   // TextAndClick(loginOrSignUp = {
   //     firebaseViewModel.logOut()
   //     throwOut()
   //     },
   //     text = "Logout",
   //     colorText = colorResource(id = R.color.blueberry_ble))


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        

        item {
            SpaceEm(height = 10.dp)
            ProfilePicAndInfo()
        }

        item {
            StatCard()
        }
    }


}

@Composable
fun SpaceEm(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}
@Composable
fun ProfilePicAndInfo( ) {
    StyleCard(path = pathToImages().profileImagePlaceHolder, height =  100, width =  100)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StyleCard(path: String, height: Int, width: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .shadow(8.dp, shape = RoundedCornerShape(2.dp)), // Adds shadow with rounded corners
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Light background
        ),
        shape = RoundedCornerShape(5.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    )

    {

        Box {
            GlideImage(
                model = pathToImages().noodlePic,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height.dp) // Set the height of the image to match the card
                    .blur(3.dp) ,// Apply blur effect
                contentScale = ContentScale.Crop
            )
        Row {
            loadImageAndClick(path = path, height = height, width = width)

            Spacer(modifier = Modifier.padding(5.dp))
            Column {
                Text("Username",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 17.sp, // Larger font size
                        fontWeight = FontWeight.Medium, // Medium text weight
                        color = Color.White
                    ))

                SpaceEm(height = 15.dp)

                Text("Member since: ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 17.sp, // Larger font size
                        fontWeight = FontWeight.Medium, // Medium text weight
                        color = Color.White
                    ))

                Text("Check-ins: ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 17.sp, // Larger font size
                        fontWeight = FontWeight.Medium, // Medium text weight
                        color = Color.White
                    ))
            }

        }

    }
}
}

@Composable
fun StatCard() {
 Card(
     modifier = Modifier
         .fillMaxWidth()
         .padding(7.dp)
         .shadow(8.dp, shape = RoundedCornerShape(2.dp)), // Adds shadow with rounded corners
     colors = CardDefaults.cardColors(
         containerColor = Color(0xFFF5F5F5) // Light background
     ),
     shape = RoundedCornerShape(5.dp), // Rounded corners
     elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
 ) {

 }
}