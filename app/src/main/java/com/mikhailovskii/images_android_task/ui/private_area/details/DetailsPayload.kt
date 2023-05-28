package com.mikhailovskii.images_android_task.ui.private_area.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailsPayload(
    val id: Long? = null,
    val url: String? = null,
    val size: UInt? = null,
    val type: String? = null,
    val tags: String? = null,
    val userName: String? = null,
    val views: UInt? = null,
    val likes: UInt? = null,
    val comments: UInt? = null,
    val favorites: UInt? = null,
    val downloads: UInt? = null,
) : Parcelable