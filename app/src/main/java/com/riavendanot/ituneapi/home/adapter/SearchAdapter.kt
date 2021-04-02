package com.riavendanot.ituneapi.home.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.riavendanot.ituneapi.home.adapter.base.AdapterTypes
import com.riavendanot.ituneapi.home.adapter.base.ViewType
import com.riavendanot.ituneapi.home.adapter.base.ViewTypeDelegateAdapter

class SearchAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val emptyErrorItem = object : ViewType {
        override fun getViewType(): Int = AdapterTypes.EMPTY_ERROR
    }

    init {
        delegateAdapter.put(AdapterTypes.EMPTY_ERROR, EmptyErrorDelegateAdapter())
        items = ArrayList()
        items.add(emptyErrorItem)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapter.get(viewType)?.onCreateViewHolder(parent) ?:
        throw IllegalArgumentException("Invalid view type")

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegateAdapter.get(getItemViewType(position))?.onBindViewHolder(holder, this.items[position]) ?:
        throw IllegalArgumentException("Invalid view type")

    override fun getItemViewType(position: Int): Int = this.items[position].getViewType()
}