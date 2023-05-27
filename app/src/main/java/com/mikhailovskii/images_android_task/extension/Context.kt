package com.mikhailovskii.images_android_task.extension

import android.content.Context

fun Context.getString(string: String): String {
    val packageName: String = this.packageName
    return when (val resId = this.resources.getIdentifier(string, "string", packageName)) {
        0 -> string
        else -> this.getString(resId)
    }
}