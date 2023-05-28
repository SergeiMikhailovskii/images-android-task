package com.mikhailovskii.domain.usecase.private_area

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.domain.repository.NetworkRepository

class GetImageListUseCase(
    private val repository: NetworkRepository
) : UseCase<List<HomeImageInfo>, Unit> {
    override suspend fun invoke(params: Unit) = repository.getImages()
}