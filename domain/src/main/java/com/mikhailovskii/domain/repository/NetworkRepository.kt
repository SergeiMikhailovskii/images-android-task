package com.mikhailovskii.domain.repository

import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields

interface NetworkRepository {
    fun login(fields: LoginFields)
    fun register(fields: RegistrationFields)
}