package com.simpson.goodssearch.ui.search.naver.trend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import kotlinx.android.synthetic.main.trend_view.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {
    private val itemList = ArrayList<TrendCategoryAdapter.TrendItem>()

    class ItemHolder(parent:ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.trend_view, parent, false)
    ) {
        fun onBind(items: List<TrendCategoryAdapter.TrendItem>){
            (itemView.list_results.adapter as TrendCategoryAdapter).clear()
            itemView.run {
                for (item in items) {
                    (itemView.list_results.adapter as TrendCategoryAdapter).addTrendItem(item)
                }
            }
            (itemView.list_results.adapter as TrendCategoryAdapter).notifyDataSetChanged()
        }
    }

    fun clear() {
        val itemSize = itemList.size
        itemList.clear()
        notifyItemRangeRemoved(0, itemSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        println("RecyclerViewAdapter.onCreateViewHolder")
        val itemHolder = ItemHolder(parent)
        itemHolder.itemView.list_results.adapter = TrendCategoryAdapter()
        itemHolder.itemView.list_results.layoutManager = LinearLayoutManager(parent.context)
        return itemHolder
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList)
    }

    fun addTrendItem(title: String, period: String, ratio: Float){
        println("RecyclerViewAdapter.addTrendItem = ( $title, $period, $ratio )")
        itemList.add(TrendCategoryAdapter.TrendItem(title, period, ratio))
    }

    fun addTrendItem(lists: ArrayList<TrendCategoryAdapter.TrendItem>){
        itemList.addAll(lists)
        println("RecyclerViewAdapter.addTrendItem = $lists")
    }

    override fun onViewRecycled(holder: ItemHolder) {
        println("RecyclerViewAdapter.onViewRecycled = $holder")
        super.onViewRecycled(holder)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        println("RecyclerViewAdapter.onViewRecycled = $recyclerView")
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onViewDetachedFromWindow(holder: ItemHolder) {
        println("RecyclerViewAdapter.onViewRecycled = $holder")
        super.onViewDetachedFromWindow(holder)
    }
}