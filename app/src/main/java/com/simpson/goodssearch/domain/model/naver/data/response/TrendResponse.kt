package com.simpson.goodssearch.domain.model.naver.data.response

data class TrendResponse (
    var startDate: String,
    var endDate: String,
    var timeUnit: String,
    var results:ArrayList<Result>,
) {
    data class Result(
        var title:String,
        var keywords:List<String>,
        var data:List<Data>
    ) {
        data class Data(
            var period: String,
            var ratio: Float
        )
    }
}