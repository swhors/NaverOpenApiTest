package com.simpson.goodssearch.domain.model.mygoods.data

import java.util.*

data class MyGoods(
    var id: Int,
    var goods_name: String, var goods_id: Long,
    var goods_url: String, var image_url: String,
    var mall_name: String, var lprice: Int,
    var hprice: Int, var goods_date: Long) {

    constructor() : this(0, "", -1L, "",
        "", "", -1,
        -1, -1L)

    constructor(builder: Builder) : this(
        builder.id!!,
        builder.goods_name!!,
        builder.goods_id!!,
        builder.goods_url!!,
        builder.image_url!!,
        builder.mall_name!!,
        builder.lprice!!,
        builder.hprice!!,
        builder.goods_date!!
    )
    class Builder {
        var id: Int ?= 0
            private set
        var goods_name: String ?= ""
            private set
        var goods_id: Long ?= -1L
            private set
        var goods_url: String ?= ""
            private set
        var image_url: String ?= ""
            private set
        var mall_name: String ?= ""
            private set
        var lprice: Int ?= -1
            private set
        var hprice: Int ?= -1
            private set
        var goods_date: Long ?= -1L
            private set

        fun id(id: Int) = apply { this.id = id }
        fun goods_name(goods_name: String) = apply { this.goods_name = goods_name }
        fun goods_id(goods_id: Long) = apply { this.goods_id = goods_id }
        fun goods_url(goods_url: String) = apply { this.goods_url = goods_url }
        fun image_url(image_url: String) = apply { this.image_url = image_url }
        fun mall_name(mall_name: String) = apply { this.mall_name = mall_name }
        fun lprice(lprice: Int) = apply { this.lprice = lprice }
        fun hprice(hprice: Int) = apply { this.hprice = hprice }
        fun goods_date(goods_date: Long) = apply { this.goods_date = goods_date}

        fun builder(): MyGoods {
            if (goods_date!! <= 0L)
                this.goods_date = Date().time

            return MyGoods(this)
        }

    }
}