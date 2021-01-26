package com.simpson.goodssearch.domain.model.kakao

import com.simpson.goodssearch.domain.model.kakao.enum.KakaoSearchSortEnum
import com.simpson.goodssearch.domain.model.kakao.response.ImageSearchResponse
import com.simpson.goodssearch.domain.model.kakao.response.WebSearchResponse
import com.simpson.goodssearch.domain.model.kakao.service.KakaoSearchService
import io.reactivex.Single

class KakaoDataModelImpl(private val service:KakaoSearchService): KakaoDataModel {

    private val KAKAO_APP_KEY = "5dfc952ea6659cf84db9a3dfc8c979d0"

    override fun getDataImage(query:String,
                         sort:KakaoSearchSortEnum,
                         page:Int,
                         size:Int):
            Single<ImageSearchResponse> {
        return service.searchImage(
                auth = "KakaoAK $KAKAO_APP_KEY",
                query = query,
                sort = sort.sort,
                page = page,
                size = size)
    }

    override fun getWeb(query: String,
                        sort: KakaoSearchSortEnum,
                        page: Int,
                        size: Int): Single<WebSearchResponse> {
        return service.searchWeb(
                auth = "KakaoAK $KAKAO_APP_KEY",
                query = query,
                sort = sort.sort,
                page = page,
                size = size
        )
    }
}