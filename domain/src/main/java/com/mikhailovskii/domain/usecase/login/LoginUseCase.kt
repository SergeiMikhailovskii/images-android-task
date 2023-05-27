package com.mikhailovskii.domain.usecase.login

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.repository.NetworkRepository

class LoginUseCase(
    private val repository: NetworkRepository
) : UseCase<Unit, LoginFields> {
    override suspend fun invoke(params: LoginFields) {
        repository.login(params)
    }
}