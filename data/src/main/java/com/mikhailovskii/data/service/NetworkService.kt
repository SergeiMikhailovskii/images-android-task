package com.mikhailovskii.data.service

import com.mikhailovskii.data.model.private_area.ImagesListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetworkService {
    @GET("api/?key=1631539-db8210cabd2636c6df59812df")
    fun getImages(): Call<ImagesListResponse>
}

fun buildService(): NetworkService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(NetworkService::class.java)
}