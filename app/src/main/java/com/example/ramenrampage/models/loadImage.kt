package com.example.ramenrampage.models

import androidx.compose.foundation.clickable
import com.example.ramenrampage.R


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


// loadImage will load an image using http request and fetch the image you set as the path other param is to modify the rendering og that image
// add to android_manifest.xml -->  <uses-permission android:name="android.permission.INTERNET"/> to allow internet usage
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun loadImage(path: String, height: Int, width: Int){
    GlideImage(model = path, contentDescription = "Load Image",
        modifier = Modifier
            .size(height.dp, width.dp)) {
        it.error(R.drawable.ic_launcher_foreground)
            .load(path)
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun loadImageAndClick(path: String, height: Int, width: Int) {
    GlideImage(
        model = path,
        contentDescription = "Load Image",
        modifier = Modifier
            .clickable { /* Handle click here */ }
            .size(height.dp, width.dp) // Set the size of the image
            .clip(CircleShape) // Clip to circle shape
    ) {
        it.error(R.drawable.ic_launcher_foreground)
            .load(path)
    }
}
