package com.simpson.goodssearch.ui.lib

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.simpson.goodssearch.domain.model.mygoods.data.MyGoods

abstract class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var firstClick: Long = 0L
    private var secondClick: Long = 0L
    private var count: Int = 0
    private var isDowned: Boolean = false

    private fun initSetting() {
        isDowned = false
        count = 0
        firstClick = 0L
        secondClick = 0L
    }

    abstract fun onBindItem(item: MyGoods, context: Context, itemView: View): Boolean
    abstract fun onDblClicked(item: MyGoods)

    @SuppressLint("ClickableViewAccessibility")
    fun onBind(item: MyGoods){
        itemView.run {
            if (onBindItem(item, context, itemView)) {

                itemView.setOnFocusChangeListener { _, _ ->
                    initSetting()
                }

                itemView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        isDowned = true
                    } else if (event.action == MotionEvent.ACTION_UP && isDowned) {
                        count++
                        if (count == 1) {
                            firstClick = System.currentTimeMillis()
                        } else if (count == 2) {
                            secondClick = System.currentTimeMillis()
                            if ((secondClick - firstClick) <= 1000L) {
                                onDblClicked(item)
                                initSetting()
                            } else {
                                /*
                             시간이 지난 경우에는 이값을 새로운 값으로 설정합니다.
                             처음 클릭후, 이것은 시간이 지나서 더블 클릭할 경우를
                             위한 것입니다.
                             */
                                firstClick = secondClick
                                count = 1
                            }
                        }
                    } else {
                        initSetting()
                    }
                    true
                }
            }
        }
    }
}