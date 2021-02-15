package com.simpson.goodssearch.domain.model.mygoods.api.service

import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.GoodsResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import com.simpson.goodssearch.domain.model.mygoods.api.service.ApiServiceBase.Companion.createClient
import com.simpson.goodssearch.domain.model.mygoods.data.Goods
import io.reactivex.Single

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface GoodsApiService: ApiServiceBase<GoodsApiService>{

    @POST("goods/add_goods")
    fun addGoods(
        @Body goods: Goods
    ): Single<NormalResponse>

    @FormUrlEncoded
    @POST("goods/get_goods_with_id")
    fun getGoods(
        @Field("name") name: String,
        @Field("limit") limit: Int,
        @Field("goods_id") goods_id: Long
    ): Single<GoodsResponse>

    @FormUrlEncoded
    @POST("goods/del_goods_with_id")
    fun delGoods(
        @Field("name") name: String,
        @Field("mall_name") mall_name: String,
        @Field("goods_id") goods_id: Long
    ): Single<NormalResponse>

    companion object {
        private const val BASE_API_URL = "https://192.168.4.205:5000"

        fun create(): GoodsApiService {

            return Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GoodsApiService::class.java)
        }
    }
}