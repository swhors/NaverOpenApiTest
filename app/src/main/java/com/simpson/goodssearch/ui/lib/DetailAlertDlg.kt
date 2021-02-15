package com.simpson.goodssearch.ui.lib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.simpson.goodssearch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_alert_dialog.view.text_hprice
import kotlinx.android.synthetic.main.detail_alert_dialog.view.text_lprice
import kotlinx.android.synthetic.main.detail_alert_dialog.view.text_mall
import kotlinx.android.synthetic.main.detail_alert_dialog.view.text_title
import kotlinx.android.synthetic.main.detail_alert_dialog.view.image_goods

class DetailAlertDlg(context: Context,
                     title: String,
                     mall: String,
                     lprice: String,
                     hprice: String,
                     imageUrl: String
                    ) : AlertDialog.Builder(context) {
    init {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val detailView: View = inflater.inflate(R.layout.detail_alert_dialog, null)

        Picasso.Builder(context).build()
            .load(imageUrl)
            .into(detailView.image_goods)
        detailView.text_title.text = title
        detailView.text_mall.text = mall
        detailView.text_lprice.text = lprice
        detailView.text_hprice.text = hprice
        setView(detailView)
        setCancelable(true)
        setPositiveButton("OK", null)
    }


    companion object {
        private fun convertDpToPixel(dp: Float, context: Context): Float {
            return dp * (context.resources.displayMetrics.densityDpi * 160f)
        }
        private fun convertPixelToDp(px: Float, context: Context): Float {
            return px * (context.resources.displayMetrics.densityDpi * 160f)
        }

        fun doModal(context: Context,
                    title: String,
                    mall: String,
                    lprice: String,
                    hprice: String,
                    imageUrl: String): AlertDialog {

            return DetailAlertDlg(context, title, mall, lprice, hprice, imageUrl).create()
        }
    }
}