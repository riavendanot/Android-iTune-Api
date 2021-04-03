package com.riavendanot.ituneapi.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riavendanot.ituneapi.common.coroutines.CoroutinesContextProvider
import com.riavendanot.ituneapi.data.mapper.ResultResponseToResultMapper
import com.riavendanot.ituneapi.data.network.ITunesServicesHelper
import com.riavendanot.ituneapi.data.network.RetrofitBuilder
import com.riavendanot.ituneapi.data.repository.ITunesDataRepository
import com.riavendanot.ituneapi.domain.usecase.ITunesUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(ITunesUseCase(ITunesDataRepository(ITunesServicesHelper(RetrofitBuilder.apiServices), ResultResponseToResultMapper(), CoroutinesContextProvider().io))) as T
    }

}