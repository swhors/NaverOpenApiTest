package com.simpson.goodssearch.domain.model.mygoods.api.model

import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.GoodsResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import com.simpson.goodssearch.domain.model.mygoods.data.Goods
import io.reactivex.Single

interface ApiGoodsModel {
    fun getGoods(name: String, limit: Int, goods_id: Long): Single<GoodsResponse>
    fun addGoods(goods: Goods): Single<NormalResponse>
    fun delGoods(name: String, mall_name: String, goods_id: Long): Single<NormalResponse>
}