package com.simpson.goodssearch.domain.model.mygoods.api.response

import com.simpson.goodssearch.domain.model.mygoods.data.Category

data class CategoryResponse (
    var categories: ArrayList<Category>,
    var len: Int
) {
}