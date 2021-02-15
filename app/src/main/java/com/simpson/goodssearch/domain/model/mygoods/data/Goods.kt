package com.simpson.goodssearch.domain.model.mygoods.data

import java.util.Date

data class Goods(val id: Int, val name: String,
                 val goods_url: String, val goods_id: Long,
                 val image_url: String, val mall_name: String,
                 val lprice: Long, val hprice: Long,
                 val cnt: Int, val updated: Long,
                 val created: Long) {
    constructor(builder: Builder): this(builder.id, builder.name!!,
        builder.goods_url!!, builder.goods_id,
        builder.image_url!!, builder.mall_name!!,
        builder.lprice, builder.hprice,
        builder.cnt, builder.updated,
        builder.created)

    class Builder {
        var id: Int = -1
            private set
        var name: String ?= ""
            private set
        var goods_url: String ?= ""
            private set
        var goods_id: Long = -1L
            private set
        var image_url: String ?= ""
            private set
        var mall_name: String ?= ""
            private set
        var lprice: Long = 0L
            private set
        var hprice: Long = 0L
            private set
        var cnt: Int = 0
            private set
        var updated: Long = Date().time
            private set
        var created: Long = 0L
            private set

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun goods_url(goods_url: String) = apply { this.goods_url = goods_url }
        fun goods_id(goods_id: Long) = apply { this.goods_id = goods_id }
        fun image_url(image_url: String) = apply { this.image_url = image_url }
        fun mall_name(mall_name: String) = apply { this.mall_name = mall_name }
        fun lprice(lprice: Long) = apply { this.lprice = lprice }
        fun hprice(hprice: Long) = apply { this.hprice = hprice }
        fun cnt(cnt: Int) = apply { this.cnt = cnt }
        fun updated(updated: Long) = apply { this.updated = updated }
        fun created(created: Long) = apply { this.created = created }

        fun builder() = Goods(this)
    }
}