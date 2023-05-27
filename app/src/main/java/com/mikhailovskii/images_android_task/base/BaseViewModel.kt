package com.mikhailovskii.images_android_task.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailovskii.images_android_task.failure.Failure
import com.mikhailovskii.images_android_task.route.Route

internal abstract class BaseViewModel : ViewModel() {

    private val _routeLiveData = MutableLiveData<Route>()
    internal val routeLiveData: LiveData<Route> = _routeLiveData

    private val _failureLiveData = MutableLiveData<Failure>()
    internal val failureLiveData: LiveData<Failure> = _failureLiveData

    protected fun handleRoute(route: Route) {
        _routeLiveData.value = route
    }
}
