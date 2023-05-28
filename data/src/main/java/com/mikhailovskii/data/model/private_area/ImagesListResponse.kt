package com.mikhailovskii.data.model.private_area

import com.google.gson.annotations.SerializedName

class ImagesListResponse(
    @SerializedName("hits")
    val hits: List<Hit>? = null
) {
    class Hit(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("previewURL")
        val previewUrl: String? = null,
        @SerializedName("user")
        val userName: String? = null,
        @SerializedName("imageSize")
        val size: Int? = null,
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("tags")
        val tags: String? = null,
        @SerializedName("views")
        val views: Int? = null,
        @SerializedName("likes")
        val likes: Int? = null,
        @SerializedName("comments")
        val comments: Int? = null,
        @SerializedName("collections")
        val favorites: Int? = null,
        @SerializedName("downloads")
        val downloads: Int? = null,
    )
}