package com.mikhailovskii.images_android_task.route

sealed interface Route {
    sealed interface Authorization : Route {
        object Login : Authorization
        object Registration : Authorization
    }

    sealed interface PrivateArea : Route {
        object Home : PrivateArea
        object Details : PrivateArea
    }
}