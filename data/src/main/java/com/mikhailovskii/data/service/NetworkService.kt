package com.mikhailovskii.data.service

import com.mikhailovskii.data.BuildConfig
import com.mikhailovskii.data.model.private_area.ImagesListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

fun interface NetworkService {
    @GET("api/?key=${BuildConfig.API_KEY}")
    fun getImages(): Call<ImagesListResponse>
}

fun buildService(): NetworkService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(NetworkService::class.java)
}