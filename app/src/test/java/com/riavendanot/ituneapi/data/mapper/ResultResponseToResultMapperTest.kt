package com.riavendanot.ituneapi.data.mapper

import com.riavendanot.ituneapi.common.ITunesMocks
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultResponseToResultMapperTest {
    private val mocks = ITunesMocks

    @Test
    fun `Transform ResultResponse To ResultDto`() {
        val result = ResultResponseToResultMapper().transform(mocks.getResultResponse())
        assertEquals(result, mocks.getResultDto())
    }

    @Test
    fun `Transform List of ResultResponse to List of ResultDTO`(){
        val listResult = ResultResponseToResultMapper().transformCollection(listOf(mocks.getResultResponse()))
        assertEquals(listResult, listOf(mocks.getResultDto()))
    }
}