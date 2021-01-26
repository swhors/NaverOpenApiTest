package com.simpson.goodssearch.domain.model.naver.data.response

import com.simpson.goodssearch.domain.model.naver.data.common.ProductId

data class GoodsResponse (var lastBuildDate: String,
                          var total: Int,
                          var start: Int,
                          val display: Int,
                          var items: ArrayList<Item>
        ) {
    data class Item(
        var title: String?,
        var link: String?,
        var image: String?,
        var lprice: Int?,
        var hprice: Int?,
        var malName: String?,
        var productId: ProductId?,
        var maker: String?,
        var brand: String?,
        var category1: String?,
        var category2: String?,
        var category3: String?,
        var category4: String?
        ) {
        constructor(builder: Builder) :
                this(builder.title, builder.link,
                    builder.image, builder.lprice,
                    builder.hprice, builder.malName,
                    builder.productId, builder.maker,
                    builder.brand, builder.category1,
                    builder.category2,
                    builder.category3,
                    builder.category4)
        class Builder {
            var title: String ?= null
                private set
            var link: String ?= null
                private set
            var image: String ?= null
                private set
            var lprice: Int ?= null
                private set
            var hprice: Int ?= null
                private set
            var malName: String ?= null
                private set
            var productId: ProductId?= null
                private set
            var maker: String ?= null
                private set
            var brand: String ?= null
                private set
            var category1: String ?= null
                private set
            var category2: String ?= null
                private set
            var category3: String ?= null
                private set
            var category4: String ?= null
                private set
            fun title(title: String) = apply { this.title = title }
            fun link(link: String) = apply { this.link = link }
            fun image(image: String) = apply { this.image = image }
            fun lprice(lprice: Int) = apply { this.lprice = lprice }
            fun hprice(hprice: Int) = apply { this.hprice = hprice }
            fun productId(productId: ProductId) = apply { this.productId = productId }
            fun maker(maker: String) = apply { this.maker = maker }
            fun brand(brand: String) = apply { this.brand = brand }
            fun category1(category1: String) = apply { this.category1 = category1 }
            fun category2(category2: String) = apply { this.category2 = category2 }
            fun category3(category3: String) = apply { this.category3 = category3 }
            fun category4(category4: String) = apply { this.category4 = category4 }
            fun build() = Item(this)
        }
    }
}