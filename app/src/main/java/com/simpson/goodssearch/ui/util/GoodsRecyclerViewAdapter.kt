package com.simpson.goodssearch.ui.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods

abstract class GoodsRecyclerViewAdapter(): RecyclerView.Adapter<GoodsItemHolder>() {
    private val itemList = ArrayList<MyGoods>()

    fun clear() {
        itemList.clear()
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: GoodsItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? GoodsItemHolder)?.onBind(itemList[position])
    }

    fun addItem(item: MyGoods) = itemList.add(item)

    fun deleteItem(position: Int)  {
        itemList.removeAt(position)
    }
}