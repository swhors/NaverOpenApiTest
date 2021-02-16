package com.simpson.goodssearch.ui.lib

import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.domain.model.mygoods.data.MyGoods
import java.util.*
import kotlin.collections.ArrayList

abstract class RecyclerViewAdapter: RecyclerView.Adapter<ItemHolder>() {
    private val itemList = ArrayList<Any>()

    fun clear() {
        itemList.clear()
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList[position])
    }

    fun addItem(item: Any) = itemList.add(item)

    fun deleteItem(position: Int)  {
        itemList.removeAt(position)
    }
}