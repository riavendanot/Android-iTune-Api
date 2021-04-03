package com.riavendanot.ituneapi.domain.usecase.base

abstract class UseCase<in Param: Any , out Response: Any> {
    abstract suspend fun  buildUseCase(param: Param? = null): Response
}