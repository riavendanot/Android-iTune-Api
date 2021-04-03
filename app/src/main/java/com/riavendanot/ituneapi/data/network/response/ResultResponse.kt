package com.riavendanot.ituneapi.data.network.response

import com.google.gson.annotations.SerializedName

data class ResultResponse (
    @SerializedName("artworkUrl100") val artwork: String? = null,
    @SerializedName("collectionName") val albumName: String? = null,
    @SerializedName("artistName") val artistName: String? = null,
    @SerializedName("trackName") val trackName: String? = null,
    @SerializedName("previewUrl") val preview: String? = null
)