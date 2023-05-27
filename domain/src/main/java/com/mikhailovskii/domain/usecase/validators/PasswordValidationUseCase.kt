package com.mikhailovskii.domain.usecase.validators

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError

class PasswordValidationUseCase : UseCase<ValidationError?, String> {
    override suspend fun invoke(params: String) =
        if (params.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
            ValidationError(
                subject = "password",
                errorMessage = "error_password_invalid"
            )
        } else null

    private companion object {
        const val PASSWORD_MIN_LENGTH = 6
        const val PASSWORD_MAX_LENGTH = 12
    }
}