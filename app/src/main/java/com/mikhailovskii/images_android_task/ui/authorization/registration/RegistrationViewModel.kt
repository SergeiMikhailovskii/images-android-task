package com.mikhailovskii.images_android_task.ui.authorization.registration

import androidx.lifecycle.viewModelScope
import com.mikhailovskii.domain.base.UseCase
import com.mikhailovskii.domain.failure.Failure
import com.mikhailovskii.domain.model.authorization.RegistrationFields
import com.mikhailovskii.images_android_task.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

internal class RegistrationViewModel(
    private val registerUseCase: UseCase<Unit, RegistrationFields>,
) : BaseViewModel() {

    private var screenData = RegistrationScreenData()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is Failure) handleFailure(throwable)
    }

    fun setEmail(email: String) {
        screenData = screenData.copy(email = email)
    }

    fun setPassword(password: String) {
        screenData = screenData.copy(password = password)
    }

    fun setAge(age: String) {
        screenData = screenData.copy(age = age.toIntOrNull() ?: 0)
    }

    fun register() {
        viewModelScope.launch(exceptionHandler) {
//            val fields = presentationMapper.mapScreenDataIntoFields(screenData)
//            loginUseCase(fields)
//            handleRoute(Route.PrivateArea.Home)
        }
    }
}