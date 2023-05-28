package com.mikhailovskii.images_android_task.ui.private_area.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.base.BaseViewModel
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getImageListUseCase: UseCase<List<HomeImageInfo>, Unit>
) : BaseViewModel() {

    private val _imagesLiveData = MutableLiveData<List<HomeImageInfo>>()
    val imagesLiveData: LiveData<List<HomeImageInfo>> = _imagesLiveData

    fun loadImages() {
        viewModelScope.launch {
            val response = getImageListUseCase(Unit)
            _imagesLiveData.value = response
        }
    }
}
