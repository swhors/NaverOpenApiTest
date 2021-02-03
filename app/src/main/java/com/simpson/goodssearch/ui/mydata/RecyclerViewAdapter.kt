package com.simpson.goodssearch.ui.mydata

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.simpson.goodssearch.R
import com.simpson.goodssearch.domain.model.mygoods.sqlite.MyGoods
import com.simpson.goodssearch.domain.model.mygoods.sqlite.SQLiteCtl
import com.simpson.goodssearch.ui.util.GoodsItemHolder
import com.simpson.goodssearch.ui.util.GoodsRecyclerViewAdapter
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

class RecyclerViewAdapter(_sqLiteCtl: SQLiteCtl): GoodsRecyclerViewAdapter() {

    private val sqLiteCtl = _sqLiteCtl
    private val itemList = ArrayList<MyGoods>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(parent, sqLiteCtl, this)
    }

    class ItemHolder(parent: ViewGroup,
                     _sqLiteCtl: SQLiteCtl,
                     _adapter: RecyclerViewAdapter):com.simpson.goodssearch.ui.util.GoodsItemHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.goods_list_view, parent, false)
    ) {
        private var sqlLiteCtl: SQLiteCtl = _sqLiteCtl
        private var adapter = _adapter

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

        private fun showAlertDialog(contentView: View, context: Context) {
            val alertDlg = AlertDialog.Builder(context)
            alertDlg.setView(contentView)
            alertDlg.setCancelable(true)
            alertDlg.setPositiveButton("OK", null)
            alertDlg.create().show()
        }

        private fun createAlertDlgContentView(title: String,
                                              mall: String,
                                              lprice: String,
                                              hprice: String,
                                              imageUrl: String):View {
            val inflater =
                itemView
                    .context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val contentView = inflater.inflate(
                R.layout.toast_view_layout,
                null
            )

            Picasso.Builder(itemView.context).build()
                .load(imageUrl)
                .into(contentView.image_goods)

            contentView.text_title.text = title
            contentView.text_mall.text = mall
            contentView.text_hprice.text = hprice
            contentView.text_lprice.text = lprice

            return contentView
        }

        private fun onDetailedItemClicked(itemView: View) {
            val contentView = createAlertDlgContentView(
                itemView.text_title.text.toString(),
                itemView.text_mall.text.toString(),
                itemView.text_lprice.text.toString(),
                itemView.text_hprice.text.toString(),
                itemView.text_image_url.text.toString())

            showAlertDialog(contentView, itemView.context)
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

        override fun onBindItem(item: MyGoods,
                                context: Context,
                                itemView: View): Boolean {
            if (UrlValidator().isValid(item.image_url)) {
                Picasso.Builder(context).build()
                    .load(item.image_url)
                    .into(itemView.image_goods)
                itemView.text_title.text = item.goods_name
                itemView.text_mall.text = item.mall_name
                itemView.text_lprice.text = item.lprice.toString()
                itemView.text_hprice.text = item.hprice.toString()
                itemView.text_good_id.text = item.goods_id.toString()
                itemView.text_id.text = item.id.toString()
                itemView.text_image_url.text = item.image_url
            } else {
                Log.i("RecyclerViewAdapter-MyGoods", "illegal image URL")
                sqlLiteCtl.delete(item.id, item.goods_id, item.goods_name, item.mall_name)
            }
            return true
        }

        override fun onDblClicked(item: MyGoods) {
            popupMenu(itemView)
        }
    }
}
