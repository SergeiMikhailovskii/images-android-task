package com.mikhailovskii.images_android_task.di

import com.mikhailovskii.images_android_task.ui.authorization.login.LoginFragment
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal fun initKoin() = startKoin {
    modules(androidKoinModule)
}

private val androidKoinModule by lazy {
    module {
        includes(authorizationModule)
    }
}

private val authorizationModule by lazy {
    module {
        includes(loginModule)
    }
}

private val loginModule by lazy {
    module {
        scope<LoginFragment> {
            viewModel {
                LoginViewModel()
            }
        }
    }
}
