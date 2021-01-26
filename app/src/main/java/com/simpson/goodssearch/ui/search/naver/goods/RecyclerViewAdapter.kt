package com.simpson.goodssearch.ui.search.naver.goods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.goods_list_view.view.text_hprice
import kotlinx.android.synthetic.main.goods_list_view.view.text_lprice
import kotlinx.android.synthetic.main.goods_list_view.view.text_title
import kotlinx.android.synthetic.main.goods_list_view_image.view.*
import android.util.Log

class RecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()

//    data class GoodItem(var title:String, var lprice: Int, var hprice: Int, val link: String, val image: String)

    class ItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view_image, parent, false)
    ) {
        val sqLiteCtl = _sqLiteCtl
        fun onBind(item: MyGoods){
            itemView.run {
                println(item)
                if ((item.image != null) && (item.image.length > 10)) {
                    Picasso.Builder(context).build().load(item.image)
                        .placeholder(R.drawable.ic_image_black_24dp).into(itemView.image_good)
                    itemView.text_title.text = item.name
                    itemView.text_lprice.text = item.lprice.toString()
                    itemView.text_hprice.text = item.hprice.toString()
                    itemView.setOnClickListener {
                        sqLiteCtl.insert(
                            item.name, item.id,
                            item.url, item.image,
                            item.mall, item.lprice,
                            item.hprice, item.date
                        )
                    }
                }
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
                MyGoods.Builder().hprice(hprice!!).lprice(lprice!!).name(title!!).builder()
        )

    fun addItem(item: MyGoods) = itemList.add(item)
}