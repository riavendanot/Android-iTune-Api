package com.riavendanot.ituneapi.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}