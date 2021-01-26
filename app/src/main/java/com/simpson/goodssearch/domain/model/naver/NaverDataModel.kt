package com.simpson.goodssearch.domain.model.naver

import com.simpson.goodssearch.domain.model.naver.data.request.SearchGoods
import com.simpson.goodssearch.domain.model.naver.data.request.SearchTrend
import com.simpson.goodssearch.domain.model.naver.data.response.GoodsResponse
import com.simpson.goodssearch.domain.model.naver.data.response.TrendResponse
import io.reactivex.Single

interface NaverDataModel {
    fun getDatas(body:String): Single<TrendResponse>
    fun getDatas(bodySearchTrend:SearchTrend): Single<TrendResponse>
    fun getGoods(searchGoods: SearchGoods): Single<GoodsResponse>
}