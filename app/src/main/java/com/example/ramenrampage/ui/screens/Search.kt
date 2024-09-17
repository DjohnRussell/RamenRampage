package com.example.ramenrampage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ramenrampage.R
import com.example.ramenrampage.models.loadImage
import com.example.ramenrampage.models.pathToImages
import com.example.ramenrampage.ui.screens.viewModels.searchViewModel


@Composable
fun Search(takeMeToDiscover : ()->Unit) {
    val viewModel = viewModel<searchViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val noodles by viewModel.noodles.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = searchText ,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("Name, Brand or Country")}
        )
        Spacer(modifier= Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(noodles) {noodles ->
                FancyNoodleCard(text1 = noodles.brand, text2 = noodles.style,noodles.brand, takeMeToDiscover )


            }
        }

        
    }

    }
@Composable
fun FancyNoodleCard(text1: String, text2: String, brand: String, takeMeToDiscover: ()-> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .shadow(8.dp, shape = RoundedCornerShape(10.dp)), // Adds shadow with rounded corners
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Light background
        ),
        shape = RoundedCornerShape(5.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            //todo save noodles to a user.
            takeMeToDiscover()
        }
    ) {
        // Content inside the card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // General padding around the content
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically // Aligns content vertically centered
            ) {
                // Conditionally load images based on the brand
                val imagePath = when (brand) {
                    "Shin" -> pathToImages().shin
                    "MrLee" -> pathToImages().mrlee
                    "Nissin" -> pathToImages().nissin
                    "Samyang" -> pathToImages().samyang
                    "Baldak" -> pathToImages().baldak

                    else -> painterResource(id = R.drawable.untitled_artwork) // Default image if the brand doesn't match
                }

                // Load the appropriate image
                loadImage(path = imagePath.toString(), height = 75, width = 75)

                Spacer(modifier = Modifier.width(40.dp)) // Spacer between image and brand text

                Text(
                    text = text1,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 17.sp, // Larger font size
                        fontWeight = FontWeight.Medium, // Medium text weight
                        color = Color(0xFF2F2F2F)
                    )
                )

                Spacer(modifier = Modifier.weight(1f)) // Spacer to push the second image to the right

                loadImage(path = pathToImages().japan, height = 40, width = 40) // Second image remains static
            }

            Spacer(modifier = Modifier.height(8.dp)) // Space between rows

            Row(
                horizontalArrangement = Arrangement.Center, // Center the style text in the row
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text2,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 15.sp, // Larger font size
                        fontWeight = FontWeight.ExtraLight,
                        color = Color(0xFF2F2F2F)
                    )
                )
            }
        }
    }
}






