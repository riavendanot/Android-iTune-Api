package com.riavendanot.ituneapi.data.network

import com.riavendanot.ituneapi.data.network.response.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApiServices {

    @GET("search")
    suspend fun searchTerm(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): SuccessResponse
}