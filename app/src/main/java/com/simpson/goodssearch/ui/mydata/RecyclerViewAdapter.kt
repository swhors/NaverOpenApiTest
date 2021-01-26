package com.simpson.goodssearch.ui.mydata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import kotlinx.android.synthetic.main.goods_list_view.view.*

class RecyclerViewAdapter(val _sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<GoodItem>()

    data class GoodItem(var title:String, var lprice: Int, var hprice: Int, val link: String, val image: String)

    class ItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view, parent, false)
    ) {
        fun onBind(item: GoodItem){
            itemView.run {
                itemView.text_title.text = item.title
                itemView.text_lprice.text = item.lprice.toString()
                itemView.text_hprice.text = item.hprice.toString()
            }
        }
    }

    fun clear() {
        itemList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder = ItemHolder(parent, sqLiteCtl)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        println("RecyclerViewAdapter.onBindViewHolder")
        (holder as? ItemHolder)?.onBind(itemList[position])
    }

    fun addItem(title: String?, lprice: Int?, hprice: Int?, link: String?, image: String?) = itemList.add(
        GoodItem(title!!, lprice!!, hprice!!, link!!, image!!))
}