package com.mikhailovskii.domain.usecase.private_area

import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.domain.repository.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetImageListUseCase(
    private val repository: NetworkRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : UseCase<List<HomeImageInfo>, Unit> {
    override suspend fun invoke(params: Unit) = withContext(ioDispatcher) { repository.getImages() }
}