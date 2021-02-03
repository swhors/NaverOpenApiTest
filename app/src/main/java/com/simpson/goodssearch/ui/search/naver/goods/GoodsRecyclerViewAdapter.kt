package com.simpson.goodssearch.ui.search.naver.goods

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.squareup.picasso.Picasso
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.simpson.goodssearch.ui.util.RecyclerViewAdapter
import kotlinx.android.synthetic.main.search_list_view.view.*
import org.apache.commons.validator.routines.UrlValidator

class GoodsRecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): RecyclerViewAdapter() {
    val sqLiteCtl = _sqLiteCtl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsItemHolder = GoodsItemHolder(parent, sqLiteCtl)

    class GoodsItemHolder(parent:ViewGroup, _sqLiteCtl: SQLiteCtl): com.simpson.goodssearch.ui.util.ItemHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.search_list_view, parent, false)
    ) {
        val sqLiteCtl = _sqLiteCtl

        private fun addItem(item: MyGoods) {
            sqLiteCtl.insert(
                item.goods_name, item.goods_id,
                item.goods_url, item.image_url,
                item.mall_name, item.lprice,
                item.hprice, item.goods_date
            )
        }

        override fun onDblClicked(item: MyGoods) {
            val alertDlg: AlertDialog.Builder = AlertDialog.Builder(itemView.context)
            alertDlg.setTitle(R.string.title_add_item)
            alertDlg.setPositiveButton(R.string.label_ok) { _, _ ->
                addItem(item)
            }
            alertDlg.setNegativeButton(R.string.label_cancel, null)
            alertDlg.show()
        }

        override fun onBindItem(item: MyGoods, context: Context, itemView: View): Boolean {
            if (UrlValidator().isValid(item.image_url)) {
                if (item.image_url.length > 10) {
                    Picasso.Builder(context).build()
                        .load(item.image_url)
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .into(itemView.image_good)
                    itemView.text_title.text = item.goods_name
                    itemView.text_lprice.text = item.lprice.toString()
                    itemView.text_hprice.text = item.hprice.toString()
                    return true
                }
            }
            return false
        }
    }
}