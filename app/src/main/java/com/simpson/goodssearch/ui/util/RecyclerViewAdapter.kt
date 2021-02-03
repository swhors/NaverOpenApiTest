package com.simpson.goodssearch.ui.util

import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods

abstract class RecyclerViewAdapter(): RecyclerView.Adapter<ItemHolder>() {
    private val itemList = ArrayList<MyGoods>()

    fun clear() {
        itemList.clear()
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList[position])
    }

    fun addItem(item: MyGoods) = itemList.add(item)

    fun deleteItem(position: Int)  {
        itemList.removeAt(position)
    }
}