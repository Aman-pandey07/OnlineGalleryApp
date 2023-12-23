package com.aman.flicker_image_gallery

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FlickerImageAPIClient {
    private lateinit var flickerImageAPIClient: FlickerImageAPI

    fun getFlickerImageAPIClient(): FlickerImageAPI {
        if (!this::flickerImageAPIClient.isInitialized) {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(100,TimeUnit.SECONDS)
                .build()
            flickerImageAPIClient = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(FlickerImageAPI::class.java)
        }
        return flickerImageAPIClient
    }
}