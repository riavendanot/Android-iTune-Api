package com.riavendanot.ituneapi.data.repository

import com.riavendanot.ituneapi.common.CoroutinesContextProviderTest
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.mapper.Transform
import com.riavendanot.ituneapi.common.ITunesMocks
import com.riavendanot.ituneapi.data.network.ITunesServicesHelper
import com.riavendanot.ituneapi.data.network.response.ResultResponse
import com.riavendanot.ituneapi.data.network.response.TrackResponse
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.entity.TrackDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doThrow
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ITunesDataRepositoryTest {

    @Mock
    private lateinit var services: ITunesServicesHelper

    @Mock
    private lateinit var resultMapper: Transform<ResultResponse, ResultDto>

    @Mock
    private lateinit var trackMapper: Transform<TrackResponse, TrackDto>


    private val repository: ITunesRepository by lazy {
        ITunesDataRepository(services, resultMapper, trackMapper, CoroutinesContextProviderTest().io)
    }

    private val mock = ITunesMocks

    @Test
    fun `Search a term - Success`(){
        runBlocking {
            `when`(services.searchTerm(anyString(), anyInt())).thenReturn(mock.getSuccessResponse())
            `when`(resultMapper.transformCollection(anyList())).thenReturn(listOf(mock.getResultDto()))

            val result = repository.searchTerms(anyString(), anyInt())
            assertTrue(result is Resource.Success<List<ResultDto>>)
        }
    }

    @Test
    fun `Search a term - Failure`(){
        runBlocking {
            doThrow(Throwable("Error")).`when`(services)

            val result = repository.searchTerms(anyString(), anyInt())
            assertTrue(result is Resource.Failure)
        }
    }

}