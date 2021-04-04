package com.riavendanot.ituneapi.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riavendanot.ituneapi.common.coroutines.CoroutinesContextProvider
import com.riavendanot.ituneapi.data.mapper.ResultResponseToResultMapper
import com.riavendanot.ituneapi.data.mapper.TrackResponseToTrackMapper
import com.riavendanot.ituneapi.data.network.ITunesServicesHelper
import com.riavendanot.ituneapi.data.network.RetrofitBuilder
import com.riavendanot.ituneapi.data.repository.ITunesDataRepository
import com.riavendanot.ituneapi.detail.DetailViewModel
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.entity.TrackDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import com.riavendanot.ituneapi.domain.usecase.ITunesAlbumUseCase
import com.riavendanot.ituneapi.domain.usecase.ITunesTrackUseCase
import com.riavendanot.ituneapi.domain.usecase.base.UseCase
import com.riavendanot.ituneapi.home.HomeViewModel

class ITunesViewModelFactory(
    private val type: ViewModelType = ViewModelType.HOME
) : ViewModelProvider.Factory {

    enum class ViewModelType {
        HOME, DETAIL
    }

    private val servicesHelper by lazy {
        ITunesServicesHelper(RetrofitBuilder.apiServices)
    }

    private val repository: ITunesRepository by lazy {
        ITunesDataRepository(
            servicesHelper,
            ResultResponseToResultMapper(),
            TrackResponseToTrackMapper(),
            CoroutinesContextProvider().io
        )
    }

    private val homeUseCase: UseCase<Pair<String, Int>, Resource<List<ResultDto>>> by lazy {
        ITunesAlbumUseCase(repository)
    }

    private val detailUseCase: UseCase<String, Resource<List<TrackDto>>> by lazy {
        ITunesTrackUseCase(repository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when(type) {
            ViewModelType.HOME -> HomeViewModel(homeUseCase) as T
            else -> DetailViewModel(detailUseCase) as T
        }

    }
}