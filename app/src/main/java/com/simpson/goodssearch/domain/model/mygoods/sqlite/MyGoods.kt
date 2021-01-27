package com.simpson.goodssearch.domain.model.mygoods.sqlite

import java.util.*

data class MyGoods(
    var no: Long,
    var name: String, var id: Long,
    var url: String, var image: String,
    var mall: String, var lprice: Int,
    var hprice: Int, var date: Long) {

    constructor() : this(-1L, "", -1L, "",
        "", "", -1,
        -1, -1L)

    constructor(builder: Builder) : this(
        builder.no!!,
        builder.name!!,
        builder.id!!,
        builder.url!!,
        builder.image!!,
        builder.mall!!,
        builder.lprice!!,
        builder.hprice!!,
        builder.date!!
    )
    class Builder {
        var no: Long ?= -1L
            private set
        var name: String ?= ""
            private set
        var id: Long ?= -1L
            private set
        var url: String ?= ""
            private set
        var image: String ?= ""
            private set
        var mall: String ?= ""
            private set
        var lprice: Int ?= -1
            private set
        var hprice: Int ?= -1
            private set
        var date: Long ?= -1L
            private set

        fun no(no: Long) = apply { this.no = no }
        fun name(name: String) = apply { this.name = name }
        fun id(id: Long) = apply { this.id = id }
        fun url(url: String) = apply { this.url = url }
        fun image(image: String) = apply { this.image = image }
        fun mall(mall: String) = apply { this.mall = mall }
        fun lprice(lprice: Int) = apply { this.lprice = lprice }
        fun hprice(hprice: Int) = apply { this.hprice = hprice }
        fun date(data: Long) = apply { this.date = date}

        fun builder(): MyGoods {
            if (date!! <= 0L)
                this.date == Date().time

            return MyGoods(this)
        }

    }
}