package com.riavendanot.ituneapi.data.repository

import com.riavendanot.ituneapi.common.mapper.Transform
import com.riavendanot.ituneapi.data.network.ITunesServicesHelper
import com.riavendanot.ituneapi.data.network.response.ResultResponse
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ITunesDataRepository(
    private val services: ITunesServicesHelper,
    private val resultMapper: Transform<ResultResponse, ResultDto>,
    private val dispatcherIO: CoroutineDispatcher
): ITunesRepository {

    override suspend fun searchTerms(terms: String, offset: Int): Resource<List<ResultDto>> {
        return withContext(dispatcherIO) {
            try {
                val result = services.searchTerm(terms, offset).resultList?.let {
                    resultMapper.transformCollection(it)
                } ?: emptyList()
                Resource.Success(result)
            } catch (e: Throwable) {
                Resource.Failure(e)
            }
        }
    }
}