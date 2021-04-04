package com.riavendanot.ituneapi.domain.usecase

import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import com.riavendanot.ituneapi.domain.usecase.base.UseCase

class ITunesUseCase(
    private val repository: ITunesRepository
): UseCase<Pair<String,Int>, Resource<List<ResultDto>>>() {

    override suspend fun buildUseCase(param: Pair<String, Int>?): Resource<List<ResultDto>> {
        return try {
            repository.searchTerms(param?.first.value(), param?.second.value())
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }

}