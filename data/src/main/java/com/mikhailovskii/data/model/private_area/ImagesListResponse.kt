package com.mikhailovskii.data.model.private_area

import com.google.gson.annotations.SerializedName

class ImagesListResponse(
    @SerializedName("hits")
    val hits: List<Hit>
) {
    class Hit(
        @SerializedName("previewURL")
        val previewUrl: String,
        @SerializedName("user")
        val userName: String
    )
}