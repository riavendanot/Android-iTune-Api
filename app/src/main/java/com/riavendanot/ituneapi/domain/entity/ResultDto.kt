package com.riavendanot.ituneapi.domain.entity

import android.os.Parcelable
import com.riavendanot.ituneapi.home.adapter.base.AdapterTypes
import com.riavendanot.ituneapi.home.adapter.base.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultDto(
    val artwork: String,
    val albumName: String,
    val artistName: String,
    val trackName: String,
    val preview: String
): ViewType, Parcelable {
    override fun getViewType(): Int = AdapterTypes.RESULT
}