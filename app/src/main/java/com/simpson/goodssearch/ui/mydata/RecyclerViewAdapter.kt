package com.simpson.goodssearch.ui.mydata

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_search_data.view.*
import kotlinx.android.synthetic.main.goods_list_view.view.*
import org.apache.commons.validator.routines.UrlValidator

class RecyclerViewAdapter(val _sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()

    class ItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view, parent, false)
    ) {
        private var sqlLiteCtl: SQLiteCtl = _sqLiteCtl
        private var parentViewGroup: ViewGroup = parent
        fun onBind(item: MyGoods){
            itemView.run {
                if (UrlValidator().isValid(item.image)) {
                    itemView.text_title.text = item.name
                    itemView.text_lprice.text = item.lprice.toString()
                    itemView.text_hprice.text = item.hprice.toString()
                } else {
                    Log.i("RecyclerViewAdapter-MyGoods", "illegal image URL")
                    sqlLiteCtl.delete(item.id, item.name)
                }
            }
        }
    }

    fun clear() {
        itemList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(parent, sqLiteCtl)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList[position])
//        (holder as? ItemHolder)?.itemView?.layoutParams
//        (holder as? ItemHolder)?.itemView?.layoutParams?.width = 150
//        (holder as? ItemHolder)?.itemView?.requestLayout()
    }

    fun addItem(item: MyGoods) = itemList.add(item)
}