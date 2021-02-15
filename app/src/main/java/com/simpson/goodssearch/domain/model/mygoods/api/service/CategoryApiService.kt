package com.simpson.goodssearch.domain.model.mygoods.api.service

import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.model.mygoods.api.response.NormalResponse
import com.simpson.goodssearch.domain.model.mygoods.api.service.ApiServiceBase.Companion.createClient
import io.reactivex.Single

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface CategoryApiService : ApiServiceBase<CategoryApiService>{

    @GET("category/get_categories")
    fun getCategoryAll(): Single<CategoryResponse>

    @POST("category/inc_category_count/")
    fun incCategory(
        @Path("title") title: String
    ): Single<NormalResponse>

    @FormUrlEncoded
    @POST("category/del_category?title=food")
    fun delCategory(
        @Field("title") title: String
    ): Single<NormalResponse>

    companion object {
        private const val BASE_API_URL = "https://192.168.4.205:5000"

        fun create(): CategoryApiService {

            return Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CategoryApiService::class.java)
        }
    }
}