package com.mikhailovskii.images_android_task.ui.authorization.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikhailovskii.images_android_task.base.BaseViewModel
import com.mikhailovskii.images_android_task.route.Route

internal class LoginViewModel : BaseViewModel() {

    private var screenData = LoginScreenData()

    private val _screenDataLiveData = MutableLiveData(screenData)
    internal val screenDataLiveData: LiveData<LoginScreenData> = _screenDataLiveData

    fun setEmail(email: String) {
        screenData = screenData.copy(email = email)
    }

    fun setPassword(password: String) {
        screenData = screenData.copy(password = password)
    }

    fun login() {
        println(screenData)
    }

    fun openRegistration() {
        handleRoute(Route.Authorization.Registration)
    }
}
