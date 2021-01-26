package com.simpson.goodssearch.ui.search.naver.trend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simpson.goodssearch.domain.model.naver.NaverDataModel
import com.simpson.goodssearch.domain.model.naver.data.common.KeywordGroups
import com.simpson.goodssearch.domain.model.naver.data.request.SearchTrend
import com.simpson.goodssearch.domain.model.naver.data.response.GoodsResponse
import com.simpson.goodssearch.domain.model.naver.data.response.TrendResponse
import com.simpson.goodssearch.domain.view.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentNavTrendApiViewModel(private val modelNaver: NaverDataModel) : BaseKotlinViewModel() {
    private val _tag = "FragmentNavTrendApiViewModel"

    private val _naverSearchResponseLiveData = MutableLiveData<TrendResponse>()
    val naverGoodsResponseLiveData: LiveData<TrendResponse>
        get() = _naverSearchResponseLiveData

    fun getWebSearch(query: String, page:Int, size:Int) {
        val keyword1 = arrayListOf<String>("한글", "korean")
        val keyword2 = arrayListOf<String>("영어", "english")
        val keywordGroup1 = KeywordGroups.Builder()
            .groupName("한글")
            .keywords(keyword1).build()
        val keywordGroup2 = KeywordGroups.Builder()
            .groupName("영어")
            .keywords(keyword2).build()

        val ages = arrayListOf<String>("1" , "2")
        val keywordGroups = arrayListOf<KeywordGroups>(keywordGroup1, keywordGroup2)

        val request = SearchTrend.Builder()
            .startDate("2017-01-01")
            .endDate("2017-04-30")
            .timeUnit("month")
            .keywordGroups(keywordGroups)
            .device("pc")
            .ages(ages)
            .gender("f")
            .build()

        addDisposable(modelNaver
            .getDatas(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (results.size > 0) {
                        Log.d(_tag, "documents : $results")
                        _naverSearchResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(_tag, "response error, message : ${it.message}")
            }))
    }
}