package com.mikhailovskii.images_android_task.ui.private_area.home

import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.adapter.ImagesHomeAdapter
import com.mikhailovskii.images_android_task.ui.private_area.details.DetailsPayload

internal interface HomePresentationMapper {
    fun mapResponseListIntoAdapterList(input: List<HomeImageInfo>): List<ImagesHomeAdapter.Model>
    fun mapHomeImageInfoIntoDetailsPayload(input: HomeImageInfo): DetailsPayload
}

internal class HomePresentationMapperImpl : HomePresentationMapper {
    override fun mapResponseListIntoAdapterList(input: List<HomeImageInfo>) = input.map {
        ImagesHomeAdapter.Model(
            id = it.id ?: 0U,
            userName = it.userName.orEmpty(),
            imageUrl = it.url.orEmpty()
        )
    }

    override fun mapHomeImageInfoIntoDetailsPayload(
        input: HomeImageInfo
    ) = DetailsPayload(
        id = input.id,
        url = input.url,
        size = input.size,
        type = input.type,
        tags = input.tags,
        userName = input.userName,
        views = input.views,
        likes = input.likes,
        comments = input.comments,
        favorites = input.favorites,
        downloads = input.downloads
    )
}
