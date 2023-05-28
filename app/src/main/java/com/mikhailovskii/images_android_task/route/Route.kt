package com.mikhailovskii.images_android_task.route

import com.mikhailovskii.images_android_task.ui.private_area.details.DetailsPayload

sealed interface Route {
    sealed interface Authorization : Route {
        object Login : Authorization
        object Registration : Authorization
    }

    sealed interface PrivateArea : Route {
        object Home : PrivateArea
        class Details(
            val payload: DetailsPayload
        ) : PrivateArea
    }
}