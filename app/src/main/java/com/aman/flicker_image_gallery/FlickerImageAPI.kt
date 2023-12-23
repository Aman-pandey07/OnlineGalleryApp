package com.aman.flicker_image_gallery

import com.aman.flicker_image_gallery.model.FlickerAPIImageResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerImageAPI {
    @GET(value = "services/rest")
    suspend fun getAllImages(
        @Query(value = "method") method : String = "flickr.photos.getRecent",
        @Query(value = "per_page") perPage: Int,
        @Query(value = "page") page: Int,
        @Query(value = "api_key") apiKey: String = "6f102c62f41998d151e5a1b48713cf13",
        @Query(value = "format") format: String = "json",
        @Query(value = "nojsoncallback") noJsonCallback: Int = 1,
        @Query(value = "extras") extras: String = "url_s",
    ) : Response<FlickerAPIImageResponse>
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object FlickerImageAPIModel {
    @Provides
    fun provideFlickerImageAPI(): FlickerImageAPI = FlickerImageAPIClient.getFlickerImageAPIClient()

}