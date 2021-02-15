package com.simpson.goodssearch.domain.model.mygoods.api.model

import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import io.reactivex.Single

interface ApiCategoryModel {
    fun getCategoryAll(): Single<CategoryResponse>
    fun incCategory(title: String): Single<NormalResponse>
    fun delCategory(title: String): Single<NormalResponse>
}