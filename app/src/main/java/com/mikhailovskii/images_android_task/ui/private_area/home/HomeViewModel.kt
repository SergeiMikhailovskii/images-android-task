package com.mikhailovskii.images_android_task.ui.private_area.home

import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.model.private_area.HomeImageInfo
import com.mikhailovskii.images_android_task.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getImageListUseCase: UseCase<List<HomeImageInfo>, Unit>
) : BaseViewModel() {

    fun loadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getImageListUseCase(Unit)
        }
    }
}
