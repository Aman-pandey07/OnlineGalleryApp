package com.aman.flicker_image_gallery


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aman.flicker_image_gallery.ui.components.FlickerPhotosGrid
//import com.aman.flicker_image_gallery.ui.theme.FlickerImageGallaryTheme
import com.aman.flicker_image_gallery.ui.theme.FlickerImageGalleryTheme
import com.aman.flicker_image_gallery.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val flickerPhotosViewModel: ImageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickerPhotosGrid(imageViewModel = flickerPhotosViewModel)
        }
    }
}



