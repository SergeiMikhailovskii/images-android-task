package com.mikhailovskii.images_android_task.ui.private_area.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.adapter.ImagesHomeAdapter
import com.mikhailovskii.images_android_task.base.BaseViewModel
import com.mikhailovskii.images_android_task.route.Route
import com.mikhailovskii.images_android_task.ui.private_area.details.DetailsPayload
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getImageListUseCase: UseCase<List<HomeImageInfo>, Unit>,
    private val presentationMapper: HomePresentationMapper
) : BaseViewModel() {

    private val _imagesLiveData = MutableLiveData<List<ImagesHomeAdapter.Model>>()
    val imagesLiveData: LiveData<List<ImagesHomeAdapter.Model>> = _imagesLiveData

    fun loadImages() {
        viewModelScope.launch {
            val response = getImageListUseCase(Unit)
            val mappedList = presentationMapper.mapResponseListIntoAdapterList(response)
            _imagesLiveData.value = mappedList
        }
    }

    fun openImageDetails(imageInfo: ImagesHomeAdapter.Model) {
        handleRoute(Route.PrivateArea.Details(DetailsPayload()))
    }
}
