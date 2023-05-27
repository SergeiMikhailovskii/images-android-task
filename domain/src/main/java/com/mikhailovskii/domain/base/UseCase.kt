package com.mikhailovskii.domain.base

fun interface UseCase<out Type, in Params> where Type : Any? {
    suspend operator fun invoke(params: Params): Type
}
