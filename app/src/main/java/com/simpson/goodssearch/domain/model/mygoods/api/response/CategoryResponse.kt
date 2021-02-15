package com.simpson.goodssearch.domain.model.mygoods.api.response

import com.simpson.goodssearch.domain.model.mygoods.data.Category

data class CategoryResponse (
    var len: Int,
    var categories: List<Category>
) {
}