package com.simpson.goodssearch.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.data.Goods
import com.simpson.goodssearch.ui.lib.ItemHolder
import com.simpson.goodssearch.ui.lib.RecyclerViewAdapter

class RecyclerViewHotAdapter: RecyclerViewAdapter() {
    class HotItemHolder(parent: ViewGroup): ItemHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view, parent, false)
    ) {
        override fun onBindItem(item: Any, context: Context, itemView: View): Boolean {
            val goods: Goods = (item as Goods)
            println("${goods.name}::${goods.mall_name}::${goods.lprice}::${goods.hprice}")
            return true
        }

        override fun onDblClicked(item: Any) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HotItemHolder(parent)
}
