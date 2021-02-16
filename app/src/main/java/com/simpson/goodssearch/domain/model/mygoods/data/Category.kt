package com.simpson.goodssearch.domain.model.mygoods.data

data class Category(val title: String, val cnt: Int, val id: Long) {
    constructor(builder: Builder) : this(builder.title!!, builder.cnt, builder.id)

    class Builder {
        var id: Long = 0L
            private set
        var title: String? = null
            private set
        var cnt: Int = 0
            private set

        fun id(id: Long) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }
        fun cnt(cnt: Int) = apply { this.cnt = cnt }

        fun builder() = Category(this)

    }
}
