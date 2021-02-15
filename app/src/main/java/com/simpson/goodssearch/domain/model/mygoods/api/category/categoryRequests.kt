package com.simpson.goodssearch.domain.model.mygoods.api.category

import com.simpson.goodssearch.domain.model.naver.data.common.Sort

data class CategoryRequests (val query: String,
                             val display: Int,
                             val start: Int,
                             val sort: Sort) {
    constructor(builder: Builder) :
            this(builder.query!!,
                builder.display!!,
                builder.start!!,
                builder.sort!!)

    class Builder {
        var query: String ? = null
            private set
        var display: Int ? = null
            private set
        var start: Int ? = null
            private set
        var sort: Sort ? = null
            private set

        fun query(query: String) = apply { this.query = query}
        fun display(display: Int) = apply { this.display = display }
        fun start(start: Int) = apply { this.start = start }
        fun sort(sort: Sort) = apply { this.sort = sort }

        fun build() = CategoryRequests(this)
    }

}
