package com.simpson.goodssearch.ui.search.naver.goods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.squareup.picasso.Picasso
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.search_list_view.view.*
import org.apache.commons.validator.routines.UrlValidator

class RecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()

    class ItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.search_list_view, parent, false)
    ) {
        val sqLiteCtl = _sqLiteCtl
        var count: Int = 0
        var firstClick: Long = 0L
        var secondClick: Long = 0L
        fun onBind(item: MyGoods){
            itemView.run {
                println(item)
                if (UrlValidator().isValid(item.image_url)) {
                    if (item.image_url.length > 10) {
                        Picasso.Builder(context).build().load(item.image_url)
                            .placeholder(R.drawable.ic_image_black_24dp).into(itemView.image_good)
                        itemView.text_title.text = item.goods_name
                        itemView.text_lprice.text = item.lprice.toString()
                        itemView.text_hprice.text = item.hprice.toString()
                        itemView.setOnTouchListener { v, event ->
                            if (event.action == MotionEvent.ACTION_DOWN) {
                                if (++count == 1) {
                                    firstClick = System.currentTimeMillis()
                                } else if (count == 2) {
                                    secondClick = System.currentTimeMillis()
                                    if ((secondClick - firstClick) < 1000) {
                                        Log.i("MyGoods(Selected)", "$secondClick, $firstClick")
                                        sqLiteCtl.insert(
                                                item.goods_name, item.goods_id,
                                                item.goods_url, item.image_url,
                                                item.mall_name, item.lprice,
                                                item.hprice, item.goods_date)
                                    }
                                    count = 0
                                    firstClick = 0L
                                    secondClick = 0L
                                }
                            }
                            true
                        }
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

    fun addItem(item: MyGoods) = itemList.add(item)
}