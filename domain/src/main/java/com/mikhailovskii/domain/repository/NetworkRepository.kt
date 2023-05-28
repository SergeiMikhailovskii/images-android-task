package com.mikhailovskii.domain.repository

import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.model.private_area.HomeImageInfo

interface NetworkRepository {
    fun login(fields: LoginFields)
    fun register(fields: RegistrationFields)
    fun getImages(): List<HomeImageInfo>
}