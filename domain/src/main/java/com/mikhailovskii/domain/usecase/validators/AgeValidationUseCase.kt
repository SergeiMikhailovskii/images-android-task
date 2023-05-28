package com.mikhailovskii.domain.usecase.validators

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.ValidationError

class AgeValidationUseCase : UseCase<ValidationError?, Int> {
    override suspend fun invoke(params: Int): ValidationError? {
        return if (params !in MIN_AGE..MAX_AGE) return ValidationError("age", "error_age_invalid")
        else null
    }

    private companion object {
        const val MIN_AGE = 18
        const val MAX_AGE = 99
    }
}