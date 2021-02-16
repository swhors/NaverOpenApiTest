package com.simpson.goodssearch.ui.mydata

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.data.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.ui.lib.DetailAlertDlg
import com.simpson.goodssearch.ui.lib.ItemHolder
import com.simpson.goodssearch.ui.lib.RecyclerViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.goods_list_view.view.text_id
import kotlinx.android.synthetic.main.goods_list_view.view.text_good_id
import kotlinx.android.synthetic.main.goods_list_view.view.text_image_url
import kotlinx.android.synthetic.main.goods_list_view.view.image_goods
import kotlinx.android.synthetic.main.goods_list_view.view.text_hprice
import kotlinx.android.synthetic.main.goods_list_view.view.text_lprice
import kotlinx.android.synthetic.main.goods_list_view.view.text_mall
import kotlinx.android.synthetic.main.goods_list_view.view.text_title
import org.apache.commons.validator.routines.UrlValidator
import java.util.*

class GoodsRecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): RecyclerViewAdapter() {

    private val sqLiteCtl = _sqLiteCtl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsItemHolder {
        return GoodsItemHolder(parent, sqLiteCtl, this)
    }

    class GoodsItemHolder(parent: ViewGroup,
                     _sqLiteCtl: SQLiteCtl,
                     _adapterGoodsGoods: GoodsRecyclerViewAdapter
    ): ItemHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.goods_list_view, parent, false)
    ) {
        private var sqlLiteCtl: SQLiteCtl = _sqLiteCtl
        private var adapter = _adapterGoodsGoods

        private fun deleteItem(title: String,
                               mall: String,
                               good_id: Long,
                               id: Int) {
            sqlLiteCtl.delete(id, good_id, title, mall)
        }

        private fun onDeleteItemClicked(itemView: View) {
            val alertDlg: androidx.appcompat.app.AlertDialog.Builder =
                androidx.appcompat.app.AlertDialog.Builder(itemView.context)

            alertDlg.setTitle(R.string.title_del_item)

            alertDlg.setPositiveButton(R.string.label_ok) { _, _ ->
                Log.i("mygoods popupMenu delete",
                    "selected = $adapterPosition, $adapter")
                this.deleteItem(
                    itemView.text_title.text.toString(),
                    itemView.text_mall.text.toString(),
                    itemView.text_good_id.text.toString().toLong(),
                    itemView.text_id.text.toString().toInt()
                )
                adapter.deleteItem(adapterPosition)
                adapter.notifyItemRangeRemoved(adapterPosition, 1)
            }
            alertDlg.setNegativeButton(R.string.label_cancel, null)
            alertDlg.show()
        }

        private fun onDetailedItemClicked(itemView: View) {

            DetailAlertDlg.doModal(itemView.context,
                itemView.text_title.text.toString(),
                itemView.text_mall.text.toString(),
                itemView.text_lprice.text.toString(),
                itemView.text_hprice.text.toString(),
                itemView.text_image_url.text.toString()).show()
        }

        private fun popupMenu(itemView: View) {
            val popupMenu = PopupMenu(itemView.context, itemView)
            Log.i("mygoods popupMenu", "$popupMenu")
            popupMenu.menuInflater.inflate(R.menu.pop_menu_on_mydata, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.pop_item_delete -> {
                        onDeleteItemClicked(itemView)
                    }
                    R.id.pop_item_detail -> {
                        val info =
                            "Title: ${itemView.text_title.text}\nMall: ${itemView.text_mall.text}\nLow Price: ${itemView.text_lprice.text}"
                        Log.i("mygoods popupMenu detail", "info = $info")
                        onDetailedItemClicked(itemView)
                    }
                }
                false
            }
            popupMenu.show()
        }

        override fun onBindItem(item: Any,
                                context: Context,
                                itemView: View): Boolean {
            val myGoods = (item as MyGoods)
            if (UrlValidator().isValid(myGoods.image_url)) {
                Picasso.Builder(context).build()
                    .load(myGoods.image_url)
                    .into(itemView.image_goods)
                itemView.text_title.text = myGoods.goods_name
                itemView.text_mall.text = myGoods.mall_name
                itemView.text_lprice.text = myGoods.lprice.toString()
                itemView.text_hprice.text = myGoods.hprice.toString()
                itemView.text_good_id.text = myGoods.goods_id.toString()
                itemView.text_id.text = myGoods.id.toString()
                itemView.text_image_url.text = myGoods.image_url
            } else {
                Log.i("RecyclerViewAdapter-MyGoods", "illegal image URL")
                sqlLiteCtl.delete(myGoods.id, myGoods.goods_id, myGoods.goods_name, myGoods.mall_name)
            }
            return true
        }

        override fun onDblClicked(item: Any) {
            popupMenu(itemView)
        }
    }
}
