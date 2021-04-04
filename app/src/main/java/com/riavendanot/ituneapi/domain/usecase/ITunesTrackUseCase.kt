package com.riavendanot.ituneapi.domain.usecase

import com.riavendanot.ituneapi.common.Resource
import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.domain.entity.TrackDto
import com.riavendanot.ituneapi.domain.repository.ITunesRepository
import com.riavendanot.ituneapi.domain.usecase.base.UseCase

class ITunesTrackUseCase(
    private  val repository: ITunesRepository
) : UseCase<String, Resource<List<TrackDto>>>() {
    override suspend fun buildUseCase(param: String?): Resource<List<TrackDto>> {
        return try {
            repository.tracks(param.value())
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }

}