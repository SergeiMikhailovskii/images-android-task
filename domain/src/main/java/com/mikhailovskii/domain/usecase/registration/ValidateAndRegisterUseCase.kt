package com.mikhailovskii.domain.usecase.registration

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.authorization.RegistrationFields

class ValidateAndRegisterUseCase(
    private val validationUseCase: UseCase<Unit, RegistrationFields>,
    private val registerUseCase: UseCase<Unit, RegistrationFields>
) : UseCase<Unit, RegistrationFields> {
    override suspend fun invoke(params: RegistrationFields) {
        validationUseCase(params)
        registerUseCase(params)
    }
}