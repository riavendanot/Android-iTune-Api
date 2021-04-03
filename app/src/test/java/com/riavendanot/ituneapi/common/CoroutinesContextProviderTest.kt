package com.riavendanot.ituneapi.common

import com.riavendanot.ituneapi.common.coroutines.CoroutinesContextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesContextProviderTest: CoroutinesContextProvider(){
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
}