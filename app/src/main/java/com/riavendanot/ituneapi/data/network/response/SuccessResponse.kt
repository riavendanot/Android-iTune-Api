package com.riavendanot.ituneapi.data.network.response

import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @SerializedName("resultCount") val paging: Int? = null,
    @SerializedName("results") val resultList: List<ResultResponse>? = null
)
