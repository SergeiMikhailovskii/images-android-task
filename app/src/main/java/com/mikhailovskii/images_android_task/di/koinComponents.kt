package com.mikhailovskii.images_android_task.di

import com.mikhailovskii.data.repository.NetworkRepository
import com.mikhailovskii.data.service.buildService
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.domain.usecase.login.LoginUseCase
import com.mikhailovskii.domain.usecase.login.LoginValidationUseCase
import com.mikhailovskii.domain.usecase.login.ValidateAndLoginUseCase
import com.mikhailovskii.domain.usecase.private_area.GetImageListUseCase
import com.mikhailovskii.domain.usecase.registration.RegisterUseCase
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
import com.mikhailovskii.images_android_task.ui.authorization.registration.RegistrationPresentationMapper
import com.mikhailovskii.images_android_task.ui.authorization.registration.RegistrationPresentationMapperImpl
import com.mikhailovskii.images_android_task.ui.authorization.registration.RegistrationViewModel
import com.mikhailovskii.images_android_task.ui.private_area.home.HomeFragment
import com.mikhailovskii.images_android_task.ui.private_area.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun initKoin() = startKoin {
    modules(
        androidKoinModule,
        dataKoinModule
    )
}

private val androidKoinModule by lazy {
    module {
        includes(authorizationModule)
    }
}

private val dataKoinModule by lazy {
    module {
        includes(
            repositoryModule,
            serviceModule
        )
    }
}

private val authorizationModule by lazy {
    module {
        includes(
            loginModule,
            registrationModule,
            homeModule
        )
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
                ValidateAndLoginUseCase(
                    get(named<LoginValidationUseCase>()),
                    get(named<LoginUseCase>())
                )
            }
            scoped<UseCase<Unit, LoginFields>>(named<LoginUseCase>()) { LoginUseCase(get()) }
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
            viewModel {
                RegistrationViewModel(
                    get(named<ValidateAndRegisterUseCase>()),
                    get()
                )
            }
            scoped<UseCase<Unit, RegistrationFields>>(named<RegistrationValidationUseCase>()) {
                RegistrationValidationUseCase(
                    get(named<EmailValidationUseCase>()),
                    get(named<PasswordValidationUseCase>()),
                    get(named<AgeValidationUseCase>()),
                )
            }
            scoped<UseCase<Unit, RegistrationFields>>(named<ValidateAndRegisterUseCase>()) {
                ValidateAndRegisterUseCase(
                    get(named<RegistrationValidationUseCase>()),
                    get(named<RegisterUseCase>())
                )
            }
            scoped<UseCase<Unit, RegistrationFields>>(named<RegisterUseCase>()) { RegisterUseCase(get()) }
            scoped<UseCase<ValidationError?, String>>(named<EmailValidationUseCase>()) {
                EmailValidationUseCase()
            }
            scoped<UseCase<ValidationError?, String>>(named<PasswordValidationUseCase>()) {
                PasswordValidationUseCase()
            }
            scoped<UseCase<ValidationError?, Int>>(named<AgeValidationUseCase>()) {
                AgeValidationUseCase()
            }
            scoped<RegistrationPresentationMapper> { RegistrationPresentationMapperImpl() }
        }
    }
}

private val homeModule by lazy {
    module {
        scope<HomeFragment> {
            viewModel { HomeViewModel(get(named<GetImageListUseCase>())) }
            scoped<UseCase<List<HomeImageInfo>, Unit>>(named<GetImageListUseCase>()) { GetImageListUseCase(get()) }
        }
    }
}

private val repositoryModule by lazy {
    module {
        single<com.mikhailovskii.domain.repository.NetworkRepository> { NetworkRepository(get()) }
    }
}

private val serviceModule by lazy {
    module {
        single { buildService() }
    }
}
