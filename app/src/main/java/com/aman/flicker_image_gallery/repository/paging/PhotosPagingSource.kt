package com.aman.flicker_image_gallery.repository.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aman.flicker_image_gallery.FlickerImageAPI
import com.aman.flicker_image_gallery.FlickerImageAPIClient
import com.aman.flicker_image_gallery.model.Photo
import com.aman.flicker_image_gallery.repository.ImageRepository
import java.io.IOException

class PhotosPagingSource (private val imageRepository: FlickerImageAPI): PagingSource<Int, Photo>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val currentPageNumber = params.key ?: 1
        val flickerAPIImageResponse = imageRepository.getAllImages(page = currentPageNumber, perPage = 20)
        val nextKey = currentPageNumber + 1
        return LoadResult.Page(
            data = flickerAPIImageResponse.body()!!.photos.photo,
            prevKey = null,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}