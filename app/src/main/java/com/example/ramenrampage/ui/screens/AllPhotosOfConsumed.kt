package com.example.ramenrampage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ramenrampage.models.loadImage
import com.example.ramenrampage.models.loadImageAndClick
import com.example.ramenrampage.models.pathToImages

@Composable
fun AllPhotosOfConsumed() {
    LazyColumn {
        item {
            Column(
                modifier = Modifier.padding(13.dp)
            ) {
                Spacer(modifier = Modifier.height(13.dp))

                // Displaying multiple rows of images
                repeat(10) { // You can change this to add more rows
                    ImageRow()
                    Spacer(modifier = Modifier.height(10.dp)) // Space between rows
                }
            }
        }
        }
    }


@Composable
fun ImageRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Spacing between images
    ) {
        repeat(4) { // 4 images per row
            ImageItem(75, 75)
        }
    }
}

@Composable
fun ImageItem( heigh: Int, width: Int) {
    loadImage(
        path = pathToImages().placeHolderImage,
        height = heigh,
        width = width
    )
}