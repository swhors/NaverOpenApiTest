package com.simpson.goodssearch.domain.model.mygoods.api.model

import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import com.simpson.goodssearch.domain.model.mygoods.api.service.CategoryApiService
import io.reactivex.Single

class ApiCategoryModelImpl(private val service: CategoryApiService): ApiCategoryModel {
    override fun getCategoryAll(): Single<CategoryResponse> {
        TODO("Not yet implemented")
        /*

    override fun getDatas(body: String): Single<TrendResponse> {
        return service.searchTrend(
            bodyTrend = GsonBuilder().create().fromJson(body, SearchTrend::class.java)
        )
    }
         */
        return service.getCategoryAll()
    }

    override fun incCategory(title: String): Single<NormalResponse> {
        return service.incCategory(title)
    }

    override fun delCategory(title: String): Single<NormalResponse> {
        return service.delCategory(title)
    }
}