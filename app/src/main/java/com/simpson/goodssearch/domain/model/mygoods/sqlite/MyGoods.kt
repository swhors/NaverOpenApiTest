package com.simpson.goodssearch.domain.model.mygoods.sqlite

data class MyGoods(
    var name: String, var id: Long,
    var url: String, var image: String,
    var mall: String, var lprice: Int,
    var hprice: Int, var date: Long) {

    constructor() : this("", -1L, "",
        "", "", -1,
        -1, -1L)

    constructor(builder: Builder) : this(
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
        var name: String ?= null
            private set
        var id: Long ?= -1L
            private set
        var url: String ?= null
            private set
        var image: String ?= null
            private set
        var mall: String ?= null
            private set
        var lprice: Int ?= -1
            private set
        var hprice: Int ?= -1
            private set
        var date: Long ?= -1L
            private set

        fun name(name: String) = apply { this.name = name }
        fun id(id: Long) = apply { this.id = id }
        fun url(url: String) = apply { this.url = url }
        fun image(image: String) = apply { this.image = image }
        fun mall(mall: String) = apply { this.mall = mall }
        fun lprice(lprice: Int) = apply { this.lprice = lprice }
        fun hprice(hprice: Int) = apply { this.hprice = hprice }
        fun date(data: Long) = apply { this.date = date}

        fun builder() = MyGoods(this)
    }
}