package com.riavendanot.ituneapi.home.adapter.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.home.adapter.base.ViewType

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, pos: Int = 0)
}