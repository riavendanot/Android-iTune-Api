package com.riavendanot.ituneapi.domain.repository

import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.domain.entity.TrackDto

interface ITunesRepository {
    suspend fun searchTerms(terms: String, offset: Int): Resource<List<ResultDto>>
    suspend fun tracks(albumId:String):Resource<List<TrackDto>>
}