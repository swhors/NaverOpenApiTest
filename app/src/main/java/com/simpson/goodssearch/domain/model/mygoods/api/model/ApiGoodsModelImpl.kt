package com.simpson.goodssearch.domain.model.mygoods.api.model

import com.simpson.goodssearch.domain.model.mygoods.api.response.GoodsResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import com.simpson.goodssearch.domain.model.mygoods.api.service.GoodsApiService
import com.simpson.goodssearch.domain.model.mygoods.data.Goods
import io.reactivex.Single

class ApiGoodsModelImpl(private val service: GoodsApiService): ApiGoodsModel {

    override fun getGoods(name: String, limit: Int, goods_id: Long): Single<GoodsResponse> {
        return service.getGoods(name, limit, goods_id)
    }

    override fun addGoods(goods: Goods): Single<NormalResponse> {
        return service.addGoods(goods)
    }

    override fun delGoods(name: String, mall_name: String, goods_id: Long): Single<NormalResponse> {
        return service.delGoods(name, mall_name, goods_id)
    }
}