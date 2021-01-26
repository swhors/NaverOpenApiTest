package com.simpson.goodssearch.ui.search.naver.trend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import kotlinx.android.synthetic.main.item_list_view.view.*

class TrendCategoryAdapter: RecyclerView.Adapter<TrendCategoryAdapter.ItemHolder>() {

    data class TrendItem(var title:String, var period:String, var ratio: Float)

    class ItemHolder(parent:ViewGroup): RecyclerView
    .ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_list_view, parent, false)) {
        fun onBind(item: TrendItem){
            itemView.run {
                text_title.text = item.title
                text_date.text = item.period
                text_ratio.text = item.ratio.toString()
            }
        }
    }


    private val trendList = ArrayList<TrendItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder = ItemHolder(parent)

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("TrendCategoryAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(trendList[position])
    }

    override fun getItemCount(): Int = trendList.size

    fun clear() {
        val itemSize = trendList.size
        trendList.clear()
        notifyItemRangeRemoved(0, itemSize)
    }

    fun addTrendItem(title: String, period: String, ratio: Float){
        println("TrendCategoryAdapter.addTrendItem0 = ( $title, $period, $ratio )")
        trendList.add(TrendItem(title, period, ratio ))
    }

    fun addTrendItem(trendItems: List<TrendItem>){
        println("TrendCategoryAdapter.addTrendItem1 = $trendItems")
        trendList.addAll(trendItems)
    }

    fun addTrendItem(trendItem: TrendItem){
        println("TrendCategoryAdapter.addTrendItem2 = $trendItem")
        trendList.add(trendItem)
    }

    override fun onViewDetachedFromWindow(holder: ItemHolder) {
        println("TrendCategoryAdapter.onViewRecycled = $holder")
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: ItemHolder) {
        println("TrendCategoryAdapter.onViewRecycled = $holder")
        super.onViewRecycled(holder)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        println("TrendCategoryAdapter.onViewRecycled = $recyclerView")
        super.onDetachedFromRecyclerView(recyclerView)
    }
}
