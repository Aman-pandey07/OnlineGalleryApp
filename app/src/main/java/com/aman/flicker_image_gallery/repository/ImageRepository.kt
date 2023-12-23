package com.aman.flicker_image_gallery.repository

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aman.flicker_image_gallery.FlickerImageAPI
import com.aman.flicker_image_gallery.model.Photo
import com.aman.flicker_image_gallery.repository.paging.PhotosPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import javax.inject.Inject

class ImageRepository @Inject constructor(private val flickerImageAPIClient: FlickerImageAPI) {
//    fun getAllImages(): Flow<PagingData<Photo>> {
//        println("It is in repository")
//        return Pager(
//            config = PagingConfig(
//                pageSize = 20,
//            ),
//            initialKey = 1,
//            pagingSourceFactory = {
//                PhotosPagingSource(imageRepository = flickerImageAPIClient)
//            }
//        ).flow
//    }
    operator fun invoke(
        viewModelScope: CoroutineScope
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            initialKey = 1,
            pagingSourceFactory = {
                PhotosPagingSource(imageRepository = flickerImageAPIClient)
            }
        ).flow.cachedIn(viewModelScope)
    }
}