package com.example.ramenrampage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ramenrampage.models.loadImage
import com.example.ramenrampage.models.pathToImages
import com.example.ramenrampage.ui.screens.viewModels.searchViewModel


@Composable
fun Search() {
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
                FancyNoodleCard(text1 = "${noodles.brand}", text2 = "${noodles.style}")


            }
        }

        
    }

    }

@Composable
fun FancyNoodleCard(text1: String, text2: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(8.dp, shape = RoundedCornerShape(10.dp)), // Adds shadow with rounded corners
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5) // Light orange background 0xFFFFD580
        ),
        shape = RoundedCornerShape(10.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            //todo save noodles to a user.
        }
    ) {
        // Content inside the card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            loadImage(path = pathToImages().nissin, height = 70, width = 70 )
            // Noodle text
            Text(
                text = text1,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 17.sp, // Larger font size
                    fontWeight = FontWeight.Medium, // Bold text
                    color = Color(0xFF2F2F2F)
                )
            )
            Text(
                text = text2,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 15.sp, // Larger font size
                    fontWeight = FontWeight.ExtraLight,
                    color = Color(0xFF2F2F2F)
                )
            )
            loadImage(path = pathToImages().japen, height = 30, width = 30 )
        }
    }
}




