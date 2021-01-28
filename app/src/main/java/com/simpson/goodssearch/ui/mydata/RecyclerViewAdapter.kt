package com.simpson.goodssearch.ui.mydata

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import kotlinx.android.synthetic.main.goods_list_view.view.*
import org.apache.commons.validator.routines.UrlValidator

class RecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    private val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()

    class ItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl, _adapter: Any):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.goods_list_view, parent, false)
    ) {
        private var sqlLiteCtl: SQLiteCtl = _sqLiteCtl
        private var firstClick: Long = 0L
        private var secondClick: Long = 0L
        private var count: Int = 0
        private var adapter = _adapter as RecyclerViewAdapter

        private fun popupMenu(itemView: View) {
            val popupMenu = PopupMenu(itemView.context, itemView)
            Log.i("mygoods popupMenu", "$popupMenu")
            popupMenu.menuInflater.inflate(R.menu.pop_menu_on_mydata, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.pop_item_delete -> {
                        sqlLiteCtl.delete(itemView.text_id.text.toString().toLong(),
                            itemView.text_title.text.toString(),
                            itemView.text_mall.text.toString())
                        Log.i("mygoods popupMenu delete", "selected = $adapterPosition")
                        adapter.itemList.removeAt(adapterPosition)
                        adapter.notifyItemRangeRemoved(adapterPosition, 1)
                    }
                    R.id.pop_item_detail -> {
                        val info = "Title: ${itemView.text_title.text}\nMall: ${itemView.text_mall.text}\nLow Price: ${itemView.text_lprice.text}"
                        Toast.makeText(itemView.context, info, Toast.LENGTH_LONG).show()
                    }
                }
                false
            }
            popupMenu.show()
        }
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(item: MyGoods){
            itemView.run {
                if (UrlValidator().isValid(item.image)) {
                    itemView.text_title.text = item.name
                    itemView.text_mall.text = item.mall
                    itemView.text_lprice.text = item.lprice.toString()
                    itemView.text_hprice.text = item.hprice.toString()
                    itemView.text_id.text = item.id.toString()
                } else {
                    Log.i("RecyclerViewAdapter-MyGoods", "illegal image URL")
                    sqlLiteCtl.delete(item.id, item.name, item.mall)
                }
                itemView.setOnTouchListener{_, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        if (++count == 1) {
                            firstClick = System.currentTimeMillis()
                        } else if (count == 2){
                            secondClick = System.currentTimeMillis()
                            Log.i("mygoods selected", "$secondClick, $firstClick")
                            if ((secondClick - firstClick) <= 1000L) {
                                popupMenu(itemView)
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