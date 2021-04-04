package com.riavendanot.ituneapi.detail

import android.util.Log
import androidx.lifecycle.*
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.TrackState
import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.domain.entity.TrackDto
import com.riavendanot.ituneapi.domain.usecase.base.UseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: UseCase<String, Resource<List<TrackDto>>>
) : ViewModel() {

    private val _loaderFlag = MutableLiveData<Boolean>(false)
    val loader: LiveData<Boolean> get() = _loaderFlag

    private val _list = MutableLiveData<List<TrackDto>?>(emptyList())
    val list: LiveData<List<TrackDto>?> get() = _list

    private val _play = MutableLiveData<Boolean>(false)
    val trackState = Transformations.map(_play) {
        if (it) {
            TrackState.PLAY
        } else {
            TrackState.PAUSE
        }
    }

    fun getTracks(albumId: String) {
        viewModelScope.launch {
            _loaderFlag.value = true
            val result = try {
                useCase.buildUseCase(albumId.value())
            } catch (e: Throwable) {
                Resource.Failure(e)
            }

            _loaderFlag.value = false
            when(result) {
                is Resource.Success<List<TrackDto>> -> {
                    _list.value = result.data
                        .filter { track -> track.type == "track" }
                        .map { it }
                }
                is Resource.Failure -> {
                    //TODO -> Manage error
                    Log.d("TAG", "${result.exception.message.value()}")
                }
            }
        }
    }

    fun playOrPause() {
        _play.value = !(_play.value!!)
    }
}