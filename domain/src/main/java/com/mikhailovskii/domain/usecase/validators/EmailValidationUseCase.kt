package com.mikhailovskii.domain.usecase.validators

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError
import java.util.regex.Pattern

class EmailValidationUseCase : UseCase<ValidationError?, String> {
    override suspend fun invoke(params: String): ValidationError? {
        val emailMatcher = Pattern
            .compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$")
            .matcher(params)
        return if (!emailMatcher.matches()) {
            ValidationError(
                subject = "email",
                errorMessage = "error_email_invalid"
            )
        } else null
    }
}