package com.simpson.goodssearch.domain.model.kakao

import com.simpson.goodssearch.domain.model.kakao.enum.KakaoSearchSortEnum
import com.simpson.goodssearch.domain.model.kakao.response.ImageSearchResponse
import com.simpson.goodssearch.domain.model.kakao.response.WebSearchResponse
import io.reactivex.Single

interface KakaoDataModel {
    fun getDataImage(query:String,
                sort: KakaoSearchSortEnum,
                page:Int,
                size:Int): Single<ImageSearchResponse>

    fun getWeb(query:String,
                     sort: KakaoSearchSortEnum,
                     page:Int,
                     size:Int): Single<WebSearchResponse>
}