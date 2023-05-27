package com.mikhailovskii.domain.usecase

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.authorization.LoginFields

class ValidateAndLoginUseCase(
    private val validationUseCase: UseCase<Unit, LoginFields>
): UseCase<Unit, LoginFields> {
    override suspend fun invoke(params: LoginFields) {
        validationUseCase(params)
    }
}