package com.riavendanot.ituneapi.domain.usecase

import com.riavendanot.ituneapi.common.ITunesMocks
import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.domain.entity.ResultDto
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
class ITunesUseCaseTest {

    @Mock
    private lateinit var repository: ITunesRepository

    private val useCase by lazy {
        ITunesAlbumUseCase(repository)
    }

    private val mock = ITunesMocks

    @Test
    fun `Use case - Success`() {
        runBlocking {
            `when`(repository.searchTerms(anyString(), anyInt())).thenReturn(mock.getResourceSuccess())
            val result = useCase.buildUseCase(Pair("", 0))
            assertTrue(result is Resource.Success<List<ResultDto>>)
        }
    }
    @Test
    fun `Use case - Failure`() {
        runBlocking {
            doThrow(Throwable("Error")).`when`(repository)

            val result = useCase.buildUseCase(Pair("", 0))
            assertTrue(result is Resource.Failure)
        }
    }
}