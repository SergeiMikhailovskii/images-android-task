package com.mikhailovskii.images_android_task.ui.authorization.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.images_android_task.base.BaseViewModel
import com.mikhailovskii.images_android_task.route.Route
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginValidationUseCase: UseCase<Unit, LoginFields>,
    private val presentationMapper: LoginPresentationMapper
) : BaseViewModel() {

    private var screenData = LoginScreenData()

    private val _screenDataLiveData = MutableLiveData(screenData)
    internal val screenDataLiveData: LiveData<LoginScreenData> = _screenDataLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is Failure) handleFailure(throwable)
    }

    fun setEmail(email: String) {
        screenData = screenData.copy(email = email)
    }

    fun setPassword(password: String) {
        screenData = screenData.copy(password = password)
    }

    fun login() {
        viewModelScope.launch(exceptionHandler) {
            val fields = presentationMapper.mapScreenDataIntoFields(screenData)
            loginValidationUseCase(fields)
        }
    }

    fun openRegistration() {
        handleRoute(Route.Authorization.Registration)
    }
}
