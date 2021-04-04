package com.riavendanot.ituneapi.common.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfinityScrollListener(
        private val action: () -> Unit,
        private val layoutManager: LinearLayoutManager
): RecyclerView.OnScrollListener() {

    private var previusTotal = 0
    private var loading = true
    private var visibleThreshold = 4
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previusTotal){
                    loading = false
                    previusTotal = totalItemCount
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem + visibleThreshold) {
                action()
                loading = true
            }
        }
    }
}