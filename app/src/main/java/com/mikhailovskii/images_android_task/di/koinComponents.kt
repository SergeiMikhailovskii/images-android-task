package com.mikhailovskii.images_android_task.di

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.usecase.LoginValidationUseCase
import com.mikhailovskii.domain.usecase.ValidateAndLoginUseCase
import com.mikhailovskii.domain.usecase.validators.EmailValidationUseCase
import com.mikhailovskii.domain.usecase.validators.PasswordValidationUseCase
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginFragment
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginPresentationMapper
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginPresentationMapperImpl
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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
            viewModel { LoginViewModel(get(named<ValidateAndLoginUseCase>()), get()) }
            scoped<UseCase<Unit, LoginFields>>(named<LoginValidationUseCase>()) {
                LoginValidationUseCase(
                    get(named<EmailValidationUseCase>()),
                    get(named<PasswordValidationUseCase>()),
                )
            }
            scoped<UseCase<Unit, LoginFields>>(named<ValidateAndLoginUseCase>()) {
                ValidateAndLoginUseCase(get(named<LoginValidationUseCase>()))
            }
            scoped<UseCase<ValidationError?, String>>(named<EmailValidationUseCase>()) {
                EmailValidationUseCase()
            }
            scoped<UseCase<ValidationError?, String>>(named<PasswordValidationUseCase>()) {
                PasswordValidationUseCase()
            }
            scoped<LoginPresentationMapper> { LoginPresentationMapperImpl() }
        }
    }
}
