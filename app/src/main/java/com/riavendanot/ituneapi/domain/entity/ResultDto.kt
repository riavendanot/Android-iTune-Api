package com.riavendanot.ituneapi.domain.entity

import android.os.Parcelable
import com.riavendanot.ituneapi.common.adapter.AdapterTypes
import com.riavendanot.ituneapi.common.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultDto(
    val artwork: String,
    val albumName: String,
    val albumId: String,
    val artistName: String,
): ViewType, Parcelable {
    override fun getViewType(): Int = AdapterTypes.RESULT
}