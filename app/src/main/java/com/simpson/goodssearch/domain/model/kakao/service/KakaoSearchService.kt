package com.simpson.goodssearch.domain.model.kakao.service

import com.simpson.goodssearch.domain.model.kakao.response.ImageSearchResponse
import com.simpson.goodssearch.domain.model.kakao.response.WebSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface KakaoSearchService {
    @GET("/v2/search/image")
    fun searchImage(
        @Header("Authorization") auth:String,
        @Query("query") query:String,
        @Query("sort") sort:String,
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Single<ImageSearchResponse>

    @GET("/v2/search/web")
    fun searchWeb(
            @Header("Authorization") auth: String,
            @Query("query") query: String,
            @Query("sort") sort: String,
            @Query("page") page: Int,
            @Query("size") size: Int
    ): Single<WebSearchResponse>
}