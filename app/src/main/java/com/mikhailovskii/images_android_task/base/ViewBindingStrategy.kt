package com.mikhailovskii.images_android_task.base

import androidx.viewbinding.ViewBinding

interface ViewBindingStrategy<VB : ViewBinding> {
    val binding: VB
}