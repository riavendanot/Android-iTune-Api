package com.riavendanot.ituneapi.data.network.response

import com.google.gson.annotations.SerializedName

class SuccessTrackResponse(
    @SerializedName("resultCount") val paging: Int? = null,
    @SerializedName("results") val resultList: List<TrackResponse>? = null
)