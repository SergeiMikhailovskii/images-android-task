package com.mikhailovskii.images_android_task.ui.authorization.registration

import com.mikhailovskii.domain.model.authorization.RegistrationFields

fun interface RegistrationPresentationMapper {
    fun mapScreenDataIntoFields(screenData: RegistrationScreenData): RegistrationFields
}

class RegistrationPresentationMapperImpl : RegistrationPresentationMapper {

    override fun mapScreenDataIntoFields(
        screenData: RegistrationScreenData
    ) = RegistrationFields(
        email = screenData.email,
        password = screenData.password,
        age = screenData.age
    )
}