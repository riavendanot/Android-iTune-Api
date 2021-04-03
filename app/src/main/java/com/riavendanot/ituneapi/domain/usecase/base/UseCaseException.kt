package com.riavendanot.ituneapi.domain.usecase.base

import com.riavendanot.ituneapi.common.constant.StringConstant.EMPTY_STRING

sealed class UseCaseException : Exception() {
    class GenericException(private val errorMessage: String = EMPTY_STRING) : Exception(errorMessage)
}