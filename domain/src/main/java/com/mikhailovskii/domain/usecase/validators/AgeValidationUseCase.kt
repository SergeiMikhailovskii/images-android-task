package com.mikhailovskii.domain.usecase.validators

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError

class AgeValidationUseCase : UseCase<ValidationError?, Int> {
    override suspend fun invoke(params: Int): ValidationError? {
        return if (params !in 18..99) return ValidationError("age", "error_age_invalid")
        else null
    }
}