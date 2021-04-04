package com.riavendanot.ituneapi.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.coroutines.CoroutinesContextProvider
import com.riavendanot.ituneapi.data.mapper.ResultResponseToResultMapper
import com.riavendanot.ituneapi.data.network.ITunesServicesHelper
import com.riavendanot.ituneapi.data.network.RetrofitBuilder
import com.riavendanot.ituneapi.data.repository.ITunesDataRepository
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import com.riavendanot.ituneapi.domain.usecase.ITunesUseCase
import com.riavendanot.ituneapi.domain.usecase.base.UseCase

class HomeViewModelFactory : ViewModelProvider.Factory {

    private val servicesHelper by lazy {
        ITunesServicesHelper(RetrofitBuilder.apiServices)
    }

    private val repository: ITunesRepository by lazy {
        ITunesDataRepository(
                servicesHelper,
                ResultResponseToResultMapper(),
                CoroutinesContextProvider().io
        )
    }

    private val useCase: UseCase<Pair<String, Int>, Resource<List<ResultDto>>> by lazy {
        ITunesUseCase(repository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(useCase) as T
    }
}