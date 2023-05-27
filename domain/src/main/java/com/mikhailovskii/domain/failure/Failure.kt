package com.mikhailovskii.domain.failure

sealed class Failure(override val message: String? = null) : Throwable(message) {
    class FieldsFailure(val validationErrors: List<ValidationError>) : Failure()
}