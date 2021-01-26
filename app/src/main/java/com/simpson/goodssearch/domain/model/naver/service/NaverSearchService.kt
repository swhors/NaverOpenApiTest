package com.simpson.goodssearch.domain.model.naver.service

import com.simpson.goodssearch.domain.model.naver.data.request.SearchGoods
import com.simpson.goodssearch.domain.model.naver.data.request.SearchTrend
import com.simpson.goodssearch.domain.model.naver.data.response.GoodsResponse
import com.simpson.goodssearch.domain.model.naver.data.response.TrendResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface NaverSearchService {
    @POST("v1/datalab/search")
    fun searchTrend(
        @Body bodyTrend: SearchTrend
    ): Single<TrendResponse>

    @GET("v1/search/shop.json")
    fun searchGoods(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("sort") sort: String
    ): Single<GoodsResponse>

    companion object {
        private const val BASE_URL_NAVER_API = "https://openapi.naver.com"
        private const val CLIENT_ID = "YSjvtxikTxBBK1CuVKp2"
        private const val CLIENT_SECRET = "8GyfGz2jpb"

        fun create(): NaverSearchService {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                    .addHeader("Content-Type", "application/json")
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_NAVER_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverSearchService::class.java)
        }
    }
}