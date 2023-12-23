package com.aman.flicker_image_gallery.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.aman.flicker_image_gallery.FlickerImageAPI
import com.aman.flicker_image_gallery.R
import com.aman.flicker_image_gallery.model.Photo
import com.aman.flicker_image_gallery.ui.extensions.itemsPaging
import com.aman.flicker_image_gallery.viewmodel.ImageViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlickerPhotosGrid(imageViewModel: ImageViewModel) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Gallery")

                    }
                },
                actions = {
                    Icon(Icons.Filled.Search, contentDescription = null)

                }
            )
        }


    ) {
        val flickerRecentPhotos: LazyPagingItems<Photo> =
            imageViewModel.moviesData.collectAsLazyPagingItems()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsPaging(flickerRecentPhotos) {
                GlideImage(
                    model = it!!.url_s,
                    contentDescription = "photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )
            }
        }
    }
}