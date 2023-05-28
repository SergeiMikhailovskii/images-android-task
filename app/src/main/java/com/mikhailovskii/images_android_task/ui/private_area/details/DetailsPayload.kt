package com.mikhailovskii.images_android_task.ui.private_area.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailsPayload(
    val id: Long? = null,
    val url: String? = null,
    val size: Int? = null,
    val type: String? = null,
    val tags: String? = null,
    val userName: String? = null,
    val views: Int? = null,
    val likes: Int? = null,
    val comments: Int? = null,
    val favorites: Int? = null,
    val downloads: Int? = null,
) : Parcelable