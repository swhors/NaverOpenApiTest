package com.simpson.goodssearch.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.data.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl

class RecyclerViewHotAdapter(_sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewHotAdapter.ItemHolder>() {

    private val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()

    class ItemHolder(parent: ViewGroup, _sqLiteCtl: SQLiteCtl, _adapter: Any):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view, parent, false)
    ) {
        private var sqlLiteCtl: SQLiteCtl = _sqLiteCtl
        private var firstClick: Long = 0L
        private var secondClick: Long = 0L
        private var count: Int = 0
        private var adapter = _adapter as RecyclerViewHotAdapter

        @SuppressLint("ClickableViewAccessibility")
        fun onBind(item: MyGoods){
            itemView.run {
//                if (UrlValidator().isValid(item.image_url)) {
//                    Picasso.Builder(context).build()
//                        .load(item.image_url)
//                        .into(itemView.image_goods)
//                    itemView.text_title.text = item.goods_name
//                    itemView.text_mall.text = item.mall_name
//                    itemView.text_lprice.text = item.lprice.toString()
//                    itemView.text_hprice.text = item.hprice.toString()
//                    itemView.text_good_id.text = item.goods_id.toString()
//                    itemView.text_id.text = item.id.toString()
//                    itemView.text_image_url.text = item.image_url
//                } else {
//                    Log.i("RecyclerViewAdapter-MyGoods", "illegal image URL")
//                    sqlLiteCtl.delete(item.id, item.goods_id, item.goods_name, item.mall_name)
//                }
//                itemView.image_goods.setOnClickListener{
//                    ToastImageView.makeText(this.context, item.image_url, item.goods_name).show()
//                }
                itemView.setOnTouchListener{ _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        if (++count == 1) {
                            firstClick = System.currentTimeMillis()
                        } else if (count == 2){
                            secondClick = System.currentTimeMillis()
                            Log.i("mygoods selected", "$secondClick, $firstClick")
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

    fun clear() {
        itemList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(parent, sqLiteCtl, this as Any)
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
