package com.riavendanot.ituneapi.data.network

import com.riavendanot.ituneapi.data.network.response.SuccessResponse
import com.riavendanot.ituneapi.data.network.response.SuccessTrackResponse
import com.riavendanot.ituneapi.data.network.response.TrackResponse
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

    @GET("lookup")
    suspend fun tracks(
        @Query("id") id:String,
        @Query("entity") entity:String = "song"
    ): SuccessTrackResponse
}