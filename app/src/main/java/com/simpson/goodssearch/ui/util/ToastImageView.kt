package com.simpson.goodssearch.ui.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.simpson.goodssearch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toast_view_layout.view.*
import org.apache.commons.validator.routines.UrlValidator

class ToastImageView//        root.text_title.text = title
    (context: Context, imageUrl: String, title: String) : AlertDialog(context) {
    private var imageUrl: String ?= imageUrl
    private var title: String ?= title

    init {
        val root = this.layoutInflater.inflate(R.layout.toast_view_layout, null)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        super.onCreateView(inflater, container, savedInstanceState)

//        val root = inflater.inflate(R.layout.toast_view_layout, null)
//
//        val imageView: ImageView = root.image_goods
//        val textView: TextView = root.text_title
//        if (UrlValidator().isValid(imageUrl)) {
//            Picasso.Builder(context).build().load(imageUrl)
//                .placeholder(R.drawable.ic_image_black_24dp).into(imageView)
//        } else {
//            Picasso.Builder(context).build().load(R.mipmap.ic_error_381599)
//                .placeholder(R.drawable.ic_image_black_24dp).into(imageView)
//        }
//        textView.text = this.title
//
//        return root
//    }

    /*
        _binding = FragmentMyDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.myDatas
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = sqLiteCtl?.let { RecyclerViewAdapter(it) }
        }

        if (imm == null)
            imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        isRun = true

        CoroutineScope(Main).launch {
            do {
                updateView()
                delay(60000)
            } while(isRun)
        }

     */



    companion object {
        private fun convertDpToPixel(dp: Float, context: Context): Float {
            return dp * (context.resources.displayMetrics.densityDpi * 160f)
        }
        private fun convertPixelToDp(px: Float, context: Context): Float {
            return px * (context.resources.displayMetrics.densityDpi * 160f)
        }

        fun makeText(context: Context, imageUrl: String, title: String): ToastImageView {
            return ToastImageView(context, imageUrl, title)
        }
    }
}