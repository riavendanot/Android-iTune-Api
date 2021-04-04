package com.riavendanot.ituneapi.data.mapper

import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.common.mapper.Transform
import com.riavendanot.ituneapi.data.network.response.TrackResponse
import com.riavendanot.ituneapi.domain.entity.TrackDto

class TrackResponseToTrackMapper : Transform<TrackResponse, TrackDto>() {
    override fun transform(value: TrackResponse): TrackDto {
        return TrackDto(
            type = value.type.value(),
            track = value.track.value(),
            url = value.url.value()
        )
    }
}