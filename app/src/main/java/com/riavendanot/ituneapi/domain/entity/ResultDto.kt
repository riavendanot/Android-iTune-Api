package com.riavendanot.ituneapi.domain.entity

import com.riavendanot.ituneapi.home.adapter.base.AdapterTypes
import com.riavendanot.ituneapi.home.adapter.base.ViewType

data class ResultDto(
    val artwork: String,
    val albumName: String,
    val artistName: String,
    val trackName: String,
    val preview: String
): ViewType {
    override fun getViewType(): Int = AdapterTypes.RESULT
}