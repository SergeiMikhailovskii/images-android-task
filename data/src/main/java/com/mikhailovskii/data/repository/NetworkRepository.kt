package com.mikhailovskii.data.repository

import com.mikhailovskii.data.service.NetworkService
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.domain.repository.NetworkRepository

class NetworkRepository(
    private val networkService: NetworkService
) : NetworkRepository {
    override fun login(fields: LoginFields) {
        // here should be the api call logic, but now it is just a stub that finishes its' work with success
    }

    override fun register(fields: RegistrationFields) {
        // here should be the api call logic, but now it is just a stub that finishes its' work with success
    }

    override fun getImages(): List<HomeImageInfo> {
        val response = networkService.getImages().execute().body()
        return response?.hits?.map {
            HomeImageInfo(userName = it.userName, url = it.previewUrl)
        } ?: emptyList()
    }
}