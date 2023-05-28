package com.mikhailovskii.images_android_task.ui.private_area.home

import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.adapter.ImagesHomeAdapter

internal fun interface HomePresentationMapper {
    fun mapResponseListIntoAdapterList(input: List<HomeImageInfo>): List<ImagesHomeAdapter.Model>
}

internal class HomePresentationMapperImpl : HomePresentationMapper {
    override fun mapResponseListIntoAdapterList(input: List<HomeImageInfo>) = input.map {
        ImagesHomeAdapter.Model(
            id = it.id ?: 0U,
            userName = it.userName.orEmpty(),
            imageUrl = it.url.orEmpty()
        )
    }
}
