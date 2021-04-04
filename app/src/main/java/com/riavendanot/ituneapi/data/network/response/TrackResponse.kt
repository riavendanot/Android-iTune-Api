package com.riavendanot.ituneapi.data.network.response

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("wrapperType") val type: String? = null,
    @SerializedName("trackName") val track: String? = null,
    @SerializedName("previewUrl") val url: String? = null
)