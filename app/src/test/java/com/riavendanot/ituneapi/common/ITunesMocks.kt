package com.riavendanot.ituneapi.common

import com.riavendanot.ituneapi.data.network.response.ResultResponse
import com.riavendanot.ituneapi.data.network.response.SuccessResponse
import com.riavendanot.ituneapi.domain.entity.ResultDto

object ITunesMocks {
    fun getResultResponse() = ResultResponse(
            "https://is5-ssl.mzstatic.com/image/thumb/Music128/v4/ce/2a/e6/ce2ae6a7-d38c-0c95-7f81-2e958c27daa4/source/100x100bb.jpg",
            "In Utero (20th Anniversary) [Remastered]",
            "123456",
            "Nirvana"
    )

    fun getResultDto() = ResultDto(
            "https://is5-ssl.mzstatic.com/image/thumb/Music128/v4/ce/2a/e6/ce2ae6a7-d38c-0c95-7f81-2e958c27daa4/source/100x100bb.jpg",
            "In Utero (20th Anniversary) [Remastered]",
            "123456",
            "Nirvana")

    fun getSuccessResponse() = SuccessResponse(
            20,
            listOf(getResultResponse())
    )

    fun getResourceSuccess(): Resource<List<ResultDto>> = Resource.Success(listOf(getResultDto()))
}