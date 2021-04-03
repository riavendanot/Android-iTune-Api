package com.riavendanot.ituneapi.data.mapper

import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.common.mapper.Transform
import com.riavendanot.ituneapi.data.network.response.ResultResponse
import com.riavendanot.ituneapi.domain.entity.ResultDto

class ResultResponseToResultMapper: Transform<ResultResponse, ResultDto>() {
    override fun transform(value: ResultResponse): ResultDto {
        return ResultDto(
            artwork = value.artwork.value(),
            albumName = value.albumName.value(),
            artistName = value.artistName.value(),
            trackName = value.trackName.value(),
            preview = value.preview.value()
        )
    }
}