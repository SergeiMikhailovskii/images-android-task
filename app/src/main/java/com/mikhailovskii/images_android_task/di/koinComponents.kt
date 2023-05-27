package com.mikhailovskii.images_android_task.di

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.usecase.login.LoginValidationUseCase
import com.mikhailovskii.domain.usecase.login.ValidateAndLoginUseCase
import com.mikhailovskii.domain.usecase.registration.RegistrationValidationUseCase
import com.mikhailovskii.domain.usecase.registration.ValidateAndRegisterUseCase
import com.mikhailovskii.domain.usecase.validators.AgeValidationUseCase
import com.mikhailovskii.domain.usecase.validators.EmailValidationUseCase
import com.mikhailovskii.domain.usecase.validators.PasswordValidationUseCase
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginFragment
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginPresentationMapper
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginPresentationMapperImpl
import com.mikhailovskii.images_android_task.ui.authorization.login.LoginViewModel
import com.mikhailovskii.images_android_task.ui.authorization.registration.RegistrationFragment
import com.mikhailovskii.images_android_task.ui.authorization.registration.RegistrationViewModel
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
        includes(registrationModule)
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

private val registrationModule by lazy {
    module {
        scope<RegistrationFragment> {
            viewModel { RegistrationViewModel(get(named<ValidateAndRegisterUseCase>())) }
            scoped<UseCase<Unit, RegistrationFields>>(named<RegistrationValidationUseCase>()) {
                RegistrationValidationUseCase(
                    get(named<EmailValidationUseCase>()),
                    get(named<PasswordValidationUseCase>()),
                    get(named<AgeValidationUseCase>()),
                )
            }
            scoped<UseCase<Unit, RegistrationFields>>(named<ValidateAndRegisterUseCase>()) {
                ValidateAndRegisterUseCase(get(named<RegistrationValidationUseCase>()))
            }
            scoped<UseCase<ValidationError?, String>>(named<EmailValidationUseCase>()) {
                EmailValidationUseCase()
            }
            scoped<UseCase<ValidationError?, String>>(named<PasswordValidationUseCase>()) {
                PasswordValidationUseCase()
            }
            scoped<UseCase<ValidationError?, Int>>(named<AgeValidationUseCase>()) {
                AgeValidationUseCase()
            }
        }
    }
}
