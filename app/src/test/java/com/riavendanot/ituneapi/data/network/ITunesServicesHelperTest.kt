package com.riavendanot.ituneapi.data.network

import com.riavendanot.ituneapi.common.ITunesMocks
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ITunesServicesHelperTest {

    @Mock
    private lateinit var services: ITunesApiServices

    private val helper by lazy {
        ITunesServicesHelper(services)
    }

    private val mock = ITunesMocks

    @Test
    fun `Service success`(){
        runBlocking {
            `when`(services.searchTerm(
                term = anyString(),
                media = anyString(),
                limit = anyInt(),
                offset = anyInt())).thenReturn(mock.getSuccessResponse())

            val result = helper.searchTerm("", 20)
            assertEquals(mock.getSuccessResponse(), result)
        }
    }
}