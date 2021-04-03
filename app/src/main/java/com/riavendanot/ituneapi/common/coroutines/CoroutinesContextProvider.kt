package com.riavendanot.ituneapi.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutinesContextProvider() {
    open val io: CoroutineDispatcher by lazy { Dispatchers.IO }
}