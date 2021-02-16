package com.simpson.goodssearch.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simpson.goodssearch.domain.model.mygoods.api.model.ApiCategoryModelImpl
import com.simpson.goodssearch.domain.model.mygoods.api.response.CategoryResponse
import com.simpson.goodssearch.domain.view.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrendViewModel(private val categoryModel: ApiCategoryModelImpl) : BaseKotlinViewModel() {
    private val _tag = "TrendViewModel"
    private val _categoriesResponse = MutableLiveData<CategoryResponse>()
    val categoriesResponse: LiveData<CategoryResponse>
        get() = _categoriesResponse

    fun getData() {
        addDisposable(categoryModel
            .getCategoryAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(_tag, "documents : ${it}")
                    if (it.categories.size > 0) {
//                        Log.d(_tag, "documents : ${it.categories}")
                        _categoriesResponse.postValue(this)
                    }
                }
            }, {
                Log.d(_tag, "response error, message : ${it.message}")
            }))
    }
}
