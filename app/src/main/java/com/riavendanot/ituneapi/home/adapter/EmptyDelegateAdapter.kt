package com.riavendanot.ituneapi.home.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.R
import com.riavendanot.ituneapi.databinding.ItemEmptyListBinding
import com.riavendanot.ituneapi.home.adapter.base.ViewType
import com.riavendanot.ituneapi.home.adapter.base.ViewTypeDelegateAdapter

class EmptyDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemEmptyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmptyErrorHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, pos: Int) {
        holder as EmptyErrorHolder
        holder.bind()
    }

    private inner class EmptyErrorHolder(val binding: ItemEmptyListBinding) : RecyclerView.ViewHolder(binding.root){
        val resource: Resources = binding.root.resources

        fun bind() {
            binding.messageTextView.text = resource.getString(
                R.string.search_a_term
            )
        }
    }
}