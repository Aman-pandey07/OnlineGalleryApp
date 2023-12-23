package com.aman.flicker_image_gallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aman.flicker_image_gallery.model.Photo
import com.aman.flicker_image_gallery.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository): ViewModel(){
    var flickerRecentPhotos: Flow<PagingData<Photo>>? = null
    val moviesData: Flow<PagingData<Photo>> = imageRepository(viewModelScope)

//    fun fetchFlickerRecentPhotos(): Flow<PagingData<Photo>> {
//        println("Printing in view model function")
//        flickerRecentPhotos = imageRepository.getAllImages().cachedIn(viewModelScope)
//        println("Count flicker is ${flickerRecentPhotos}")
//        return flickerRecentPhotos!!
//    }

}