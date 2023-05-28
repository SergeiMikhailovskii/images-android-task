package com.mikhailovskii.images_android_task.ui.authorization.login

import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.model.authorization.LoginFields
import com.mikhailovskii.images_android_task.base.BaseViewModel
import com.mikhailovskii.images_android_task.route.Route
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: UseCase<Unit, LoginFields>,
    private val presentationMapper: LoginPresentationMapper
) : BaseViewModel() {

    private var screenData = LoginScreenData()

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
            loginUseCase(fields)
            handleRoute(Route.PrivateArea.Home)
        }
    }

    fun openRegistration() {
        handleRoute(Route.Authorization.Registration)
    }
}
