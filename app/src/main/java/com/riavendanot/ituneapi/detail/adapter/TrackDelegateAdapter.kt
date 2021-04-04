package com.riavendanot.ituneapi.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.common.adapter.ViewType
import com.riavendanot.ituneapi.common.adapter.ViewTypeDelegateAdapter
import com.riavendanot.ituneapi.databinding.ItemTrackBinding
import com.riavendanot.ituneapi.domain.entity.TrackDto

class TrackDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =  ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TrackHolder
        item as TrackDto
        holder.bind(item)
    }

    private inner class TrackHolder(private val binding: ItemTrackBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrackDto) {
            binding.trackTextView.text = item.track
        }
    }
}