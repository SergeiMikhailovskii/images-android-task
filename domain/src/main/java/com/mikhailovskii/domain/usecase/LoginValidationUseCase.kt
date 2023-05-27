package com.mikhailovskii.domain.usecase

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.failure.ValidationError
import com.mikhailovskii.domain.model.authorization.LoginFields

class LoginValidationUseCase : UseCase<Unit, LoginFields> {
    override suspend fun invoke(params: LoginFields) {
        val validationErrors = mutableListOf<ValidationError>()
        val password = params.password.orEmpty()

        if (password.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
            validationErrors.add(ValidationError(subject = "password", errorMessage = "error_password_length"))
        }

        if (validationErrors.isNotEmpty()) throw Failure.FieldsFailure(validationErrors)
    }

    private companion object {
        const val PASSWORD_MIN_LENGTH = 6
        const val PASSWORD_MAX_LENGTH = 12
    }
}