package com.simpson.goodssearch.domain.model.naver

import com.google.gson.GsonBuilder
import com.simpson.goodssearch.domain.model.naver.data.request.SearchGoods
import com.simpson.goodssearch.domain.model.naver.data.request.SearchTrend
import com.simpson.goodssearch.domain.model.naver.data.response.GoodsResponse
import com.simpson.goodssearch.domain.model.naver.data.response.TrendResponse
import com.simpson.goodssearch.domain.model.naver.service.NaverSearchService
import io.reactivex.Single

class NaverDataModelImpl(private val service:NaverSearchService): NaverDataModel {

    override fun getDatas(body: String): Single<TrendResponse> {
        return service.searchTrend(
            bodyTrend = GsonBuilder().create().fromJson(body, SearchTrend::class.java)
        )
    }

    override fun getDatas(bodySearchTrend: SearchTrend): Single<TrendResponse> {
        return service.searchTrend(
            bodyTrend = bodySearchTrend
        )
    }

    override fun getGoods(searchGoods: SearchGoods): Single<GoodsResponse> {
        println("NaverDataModelImpl.getGoods = $searchGoods")
        return service.searchGoods(searchGoods.query, searchGoods.display, searchGoods.start, searchGoods.sort.sort())
    }
}