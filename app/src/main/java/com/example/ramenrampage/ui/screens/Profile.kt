package com.example.ramenrampage.ui.screens


import android.widget.ProgressBar
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
import com.google.firebase.auth.FirebaseAuth
lateinit var auth: FirebaseAuth




@Composable
fun Profile(throwOut: () -> Unit, allPics: () -> Unit) {
    val firebaseViewModel: FirebaseViewModel = viewModel()

    LaunchedEffect(Unit) {
        firebaseViewModel.fetchUserProfileName() // Ensure you call this to fetch the username
        firebaseViewModel.fetchMemberSinceDate()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        

        item {
            SpaceEm(height = 10.dp)
            ProfilePicAndInfo()
        }

        item {

            ProfileNoodleCard(Heading = "Noodles")
        }

        item {
            PictureOfConsumedNoodleCard(Heading = "Photos", allPics = allPics)
        }

        item {
            TextAndClick(loginOrSignUp = {
                firebaseViewModel.logOut()
                throwOut()
            },
                text = "Logout",
                colorText = colorResource(id = R.color.blueberry_ble))
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
    val firebaseViewModel: FirebaseViewModel = viewModel()
    val username = firebaseViewModel.username
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
                contentScale = ContentScale.Crop,

            )
        Row {
            loadImageAndClick(path = path, height = height, width = width)

            Spacer(modifier = Modifier.padding(5.dp))
            Column {



                Text(
                    text = firebaseViewModel.userProfileName.value,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 18.sp, // Make the text larger for emphasis
                        fontWeight = FontWeight.Bold, // Use bold weight for a strong appearance
                        color = Color.White, // Keep the text color white, you can experiment with other colors
                        letterSpacing = 2.sp // Adds space between letters to make the text more stylish
                    ),
                    modifier = Modifier
                        .padding(1.dp) // Add padding to give the text breathing room
                        .shadow(
                            8.dp,
                            shape = RoundedCornerShape(2.dp)
                        ) // Adds a shadow for emphasis
                        .background(

                            Color(0xFF00392cf),
                            RoundedCornerShape(10.dp)
                        ) // Optional background color
                        .padding(horizontal = 3.dp, vertical = 1.dp), // Inner padding for text
                    textAlign = TextAlign.Center // Center align the text for a balanced look
                )

                SpaceEm(height = 10.dp)

                Text("Member since: "  + firebaseViewModel.memberSince.value,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 17.sp, // Larger font size
                        fontWeight = FontWeight.Medium, // Medium text weight
                        color = Color.White,

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileNoodleCard(Heading : String) {
 Card(
     modifier = Modifier
         .clickable { }
         .fillMaxWidth()
         .padding(7.dp)
         .shadow(8.dp, shape = RoundedCornerShape(2.dp)), // Adds shadow with rounded corners
     colors = CardDefaults.cardColors(
         containerColor = Color(0xFFF5F5F5) // Light background
     ),
     shape = RoundedCornerShape(5.dp), // Rounded corners
     elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
 ) {
     Row {
         Text(Heading,
             color = Color.Black,
             style = MaterialTheme.typography.headlineLarge.copy(
                 fontSize = 16.sp, // Make the text larger for emphasis
                 fontWeight = FontWeight.Bold, // Use bold weight for a strong appearance
                 letterSpacing = 2.sp,
                 
             ))

         Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null,
             modifier = Modifier.padding(3.dp, 7.dp))
     }
     
     SpaceEm(height = 18.dp)

     AnimatedProgressBar(progress = 0.10f)
     Text("Check-ins: 3/10", style = MaterialTheme.typography.bodyMedium)

 }
}

@Composable
fun AnimatedProgressBar(progress: Float) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000)
    )

    LinearProgressIndicator(
        progress = animatedProgress.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
        color = Color(0xFF228B22)
    )
}

@Composable
fun PictureOfConsumedNoodleCard(Heading : String, allPics : () -> Unit) {
    Card(
        modifier = Modifier
            .clickable { allPics() }
            .fillMaxWidth()
            .padding(7.dp)
            .shadow(8.dp, shape = RoundedCornerShape(2.dp)), // Adds shadow with rounded corners
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Light background
        ),
        shape = RoundedCornerShape(5.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Row(
            ) {
                Text(Heading,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 16.sp, // Make the text larger for emphasis
                        fontWeight = FontWeight.Bold, // Use bold weight for a strong appearance
                        letterSpacing = 2.sp,
                        
                    ))

                Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null,
                    modifier = Modifier.padding(3.dp, 7.dp))
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
            ) {
                item {

                    ImageRowInProfile()

                }
            }
        }




    }
}


@Composable
fun ImageRowInProfile() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp) // Add space between the images
    ) {
        repeat(10) { // 4 images per row
            ImageItem(120, 120)
        }
    }
}


