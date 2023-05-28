package com.mikhailovskii.data.repository

import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.repository.NetworkRepository

class NetworkRepository: NetworkRepository {
    override fun login(fields: LoginFields) {
        // here should be the api call logic, but now it is just a stub that finishes its' work with success
    }

    override fun register(fields: RegistrationFields) {
        // here should be the api call logic, but now it is just a stub that finishes its' work with success
    }
}