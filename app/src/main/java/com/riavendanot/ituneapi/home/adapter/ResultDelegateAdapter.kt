package com.riavendanot.ituneapi.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.common.extension.loadImg
import com.riavendanot.ituneapi.databinding.ItemSearchBinding
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.common.adapter.ViewType
import com.riavendanot.ituneapi.common.adapter.ViewTypeDelegateAdapter

class ResultDelegateAdapter(
    private val viewAction: OnViewSelectedListener
) : ViewTypeDelegateAdapter {

    interface OnViewSelectedListener {
        fun onItemSelected(item: ResultDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as ResultHolder
        item as ResultDto
        holder.bind(item)
        holder.itemView.setOnClickListener {
            viewAction.onItemSelected(item)
        }
    }

    private inner class ResultHolder(private val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultDto) {
            binding.albumImageView.loadImg(item.artwork)
            binding.artistTextView.text = item.artistName
            binding.albumTextView.text = item.albumName
        }
    }

}