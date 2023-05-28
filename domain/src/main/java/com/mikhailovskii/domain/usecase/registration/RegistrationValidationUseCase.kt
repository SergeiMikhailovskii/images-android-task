package com.mikhailovskii.domain.usecase.registration

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.RegistrationFields

class RegistrationValidationUseCase(
    private val emailValidationUseCase: UseCase<ValidationError?, String>,
    private val passwordValidationUseCase: UseCase<ValidationError?, String>,
    private val ageValidationUseCase: UseCase<ValidationError?, Int>,
) : UseCase<Unit, RegistrationFields> {
    override suspend fun invoke(params: RegistrationFields) {
        val validationErrors = mutableListOf<ValidationError>()
        val password = params.password.orEmpty()
        val email = params.email.orEmpty()
        val age = params.age ?: 0

        emailValidationUseCase(email)?.let(validationErrors::add)
        passwordValidationUseCase(password)?.let(validationErrors::add)
        ageValidationUseCase(age)?.let(validationErrors::add)

        if (validationErrors.isNotEmpty()) throw Failure.FieldsFailure(validationErrors)
    }
}