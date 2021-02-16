package com.simpson.goodssearch.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.data.Category
import com.simpson.goodssearch.ui.lib.RecyclerViewAdapter
import com.simpson.goodssearch.ui.lib.ItemHolder


class RecyclerViewTrendAdapter: RecyclerViewAdapter() {

    class TrendItemHolder(parent: ViewGroup): ItemHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.trend_item_list_view, parent, false)
    ) {
        override fun onBindItem(item: Any, context: Context, itemView: View): Boolean {
            val category: Category = (item as Category)
            println("${category.title}::${category.cnt}")
            return true
        }

        override fun onDblClicked(item: Any) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrendItemHolder(parent)
}
