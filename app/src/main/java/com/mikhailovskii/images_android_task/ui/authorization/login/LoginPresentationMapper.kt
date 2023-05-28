package com.mikhailovskii.images_android_task.ui.authorization.login

import com.mikhailovskii.domain.model.authorization.LoginFields

fun interface LoginPresentationMapper {
    fun mapScreenDataIntoFields(screenData: LoginScreenData): LoginFields
}

class LoginPresentationMapperImpl : LoginPresentationMapper {

    override fun mapScreenDataIntoFields(
        screenData: LoginScreenData
    ) = LoginFields(
        email = screenData.email,
        password = screenData.password
    )
}