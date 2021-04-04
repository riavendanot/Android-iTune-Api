package com.riavendanot.ituneapi.detail.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.common.adapter.AdapterTypes
import com.riavendanot.ituneapi.common.adapter.ViewType
import com.riavendanot.ituneapi.common.adapter.ViewTypeDelegateAdapter
import com.riavendanot.ituneapi.domain.entity.TrackDto

class TrackAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType>
    private var delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapter.put(AdapterTypes.TRACK, TrackDelegateAdapter())
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapter.get(viewType)?.onCreateViewHolder(parent) ?:
        throw IllegalArgumentException("Invalid view type")

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegateAdapter.get(getItemViewType(position))?.onBindViewHolder(holder, this.items[position]) ?:
        throw IllegalArgumentException("Invalid view type")

    override fun getItemViewType(position: Int): Int = this.items[position].getViewType()

    fun addItems(trackList: List<TrackDto>) {
        items.addAll(trackList)
        notifyDataSetChanged()
    }

}