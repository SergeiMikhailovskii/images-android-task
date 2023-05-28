package com.mikhailovskii.domain.usecase.registration

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.repository.NetworkRepository

class RegisterUseCase(
    private val repository: NetworkRepository
) : UseCase<Unit, RegistrationFields> {
    override suspend fun invoke(params: RegistrationFields) {
        repository.register(params)
    }
}