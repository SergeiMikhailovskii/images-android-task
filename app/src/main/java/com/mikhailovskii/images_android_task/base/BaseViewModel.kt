package com.mikhailovskii.images_android_task.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.images_android_task.route.Route

internal abstract class BaseViewModel : ViewModel() {

    private val _routeLiveData = MutableLiveData<Route>()
    val routeLiveData: LiveData<Route> = _routeLiveData

    private val _failureLiveData = MutableLiveData<Failure>()
    val failureLiveData: LiveData<Failure> = _failureLiveData

    protected fun handleRoute(route: Route) {
        _routeLiveData.value = route
    }

    protected fun handleFailure(failure: Failure) {
        _failureLiveData.value = failure
    }
}
