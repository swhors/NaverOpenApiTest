package com.simpson.goodssearch.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simpson.goodssearch.domain.model.mygoods.api.model.ApiGoodsModelImpl
import com.simpson.goodssearch.domain.model.mygoods.api.response.GoodsResponse
import com.simpson.goodssearch.domain.view.BaseKotlinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HotViewModel(private val goodsModel: ApiGoodsModelImpl) : BaseKotlinViewModel() {
    private val _tag = "HotViewModel"
    private val _goodsResponse = MutableLiveData<GoodsResponse>()
    val goodssResponse: LiveData<GoodsResponse>
        get() = _goodsResponse

    fun getData(name: String, limit_num: Int, goods_id: Long) {
        addDisposable(goodsModel
            .getGoods(name, limit_num, goods_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (it.goods.size > 0) {
                        Log.d(_tag, "documents : $it")
                        _goodsResponse.postValue(this)
                    }
                }
            }, {
                Log.d(_tag, "response error, message : ${it.message}")
            }))
    }
}