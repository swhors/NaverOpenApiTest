package com.simpson.goodssearch.domain.model.mygoods.api.response

import com.simpson.goodssearch.domain.model.mygoods.data.Goods

data class GoodsResponse (
    var len: Int,
    var goods: List<Goods>
) {
}