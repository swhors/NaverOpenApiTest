package com.simpson.goodssearch.domain.model.mygoods.data

data class Category(val id: Long,val title: String, val cnt: Int) {
    constructor(builder: Builder) : this(builder.id, builder.title!!, builder.cnt)

    class Builder {
        var id: Long = 0L
        var title: String? = null
        var cnt: Int = 0

        fun id(id: Long) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }
        fun cnt(cnt: Int) = apply { this.cnt = cnt }

        fun builder() = Category(this)

    }
}
