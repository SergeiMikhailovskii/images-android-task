package com.mikhailovskii.images_android_task

import android.app.Application
import com.mikhailovskii.images_android_task.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
