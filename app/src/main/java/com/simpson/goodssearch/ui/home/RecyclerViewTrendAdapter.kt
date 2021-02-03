package com.simpson.goodssearch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import kotlinx.android.synthetic.main.trend_item_list_view.view.*

class RecyclerViewTrendAdapter(): RecyclerView.Adapter<RecyclerViewTrendAdapter.ItemHolder>() {
    private val itemList = ArrayList<String>()

    class ItemHolder(parent: ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.trend_item_list_view, parent, false)
    ) {
        fun onBind(rows: String){
            itemView.run {
                itemView.text_title.text = rows
            }
        }
    }

    fun clear() {
        itemList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(parent)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList[position])
    }

    fun addItem(item: String) = itemList.add(item)
}
