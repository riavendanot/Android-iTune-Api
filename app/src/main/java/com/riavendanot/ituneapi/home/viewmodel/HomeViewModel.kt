package com.riavendanot.ituneapi.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.usecase.base.UseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: UseCase<String, Resource<List<ResultDto>>>
): ViewModel() {

    fun searchTerm(query: String) {
        viewModelScope.launch {
            val result = try {
                useCase.buildUseCase(query)
            } catch (e: Throwable) {
                Resource.Failure(e)
            }

            when(result) {
                is Resource.Success<List<ResultDto>> -> {Log.d("TAG", "${result.data}")}
                is Resource.Failure -> {Log.d("TAG","${result.exception.message}")}
            }
        }
    }
}