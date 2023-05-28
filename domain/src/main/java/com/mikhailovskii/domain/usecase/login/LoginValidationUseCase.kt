package com.mikhailovskii.domain.usecase.login

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.LoginFields

class LoginValidationUseCase(
    private val emailValidationUseCase: UseCase<ValidationError?, String>,
    private val passwordValidationUseCase: UseCase<ValidationError?, String>,
) : UseCase<Unit, LoginFields> {
    override suspend fun invoke(params: LoginFields) {
        val validationErrors = mutableListOf<ValidationError>()
        val password = params.password.orEmpty()
        val email = params.email.orEmpty()

        emailValidationUseCase(email)?.let(validationErrors::add)
        passwordValidationUseCase(password)?.let(validationErrors::add)

        if (validationErrors.isNotEmpty()) throw Failure.FieldsFailure(validationErrors)
    }
}