package com.simpson.goodssearch.ui.search.naver.goods

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simpson.goodssearch.domain.model.naver.NaverDataModel
import com.simpson.goodssearch.domain.model.naver.data.common.Sort
import com.simpson.goodssearch.domain.model.naver.data.request.SearchGoods
import com.simpson.goodssearch.domain.model.naver.data.response.GoodsResponse
import com.simpson.goodssearch.domain.view.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentSearchViewModel(private val modelNaver: NaverDataModel) : BaseKotlinViewModel() {
    private val _tag = "FragmentSearchViewModel"

    private val _naverSearchResponseLiveData = MutableLiveData<GoodsResponse>()
    val naverGoodsResponseLiveData: LiveData<GoodsResponse>
        get() = _naverSearchResponseLiveData

    fun search(query: String, display: Int, start:Int, sort: Sort) {
        val request = SearchGoods.Builder()
            .query(query)
            .display(display)
            .start(start)
            .sort(sort)
            .build()

        addDisposable(modelNaver
            .getGoods(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (items.size > 0) {
                        Log.d(_tag, "documents : $it")
                        _naverSearchResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(_tag, "response error, message : ${it.message}")
            }))
    }
}