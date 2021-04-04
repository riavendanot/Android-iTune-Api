package com.riavendanot.ituneapi.domain.entity

import com.riavendanot.ituneapi.common.adapter.AdapterTypes
import com.riavendanot.ituneapi.common.adapter.ViewType

data class TrackDto(
    val type: String,
    val track: String,
    val url: String
): ViewType {
    override fun getViewType(): Int = AdapterTypes.TRACK
}