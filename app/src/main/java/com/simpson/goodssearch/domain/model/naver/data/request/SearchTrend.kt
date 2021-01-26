package com.simpson.goodssearch.domain.model.naver.data.request

import com.simpson.goodssearch.domain.model.naver.data.common.KeywordGroups


data class SearchTrend (val startDate: String,
                        val endDate: String,
                        val timeUnit: String,
                        val keywordGroups: ArrayList<KeywordGroups>,
                        val device: String,
                        val ages: ArrayList<String>,
                        val gender: String) {

    constructor(builder: Builder) :
            this(builder.startDate!!,
                builder.endDate!!,
                builder.timeUnit!!,
                builder.keywordGroups!!,
                builder.device!!,
                builder.ages!!,
                builder.gender!!)
    class Builder {
        var startDate: String ? = null
            private set
        var endDate: String ? = null
            private set
        var timeUnit: String ? = null
            private set
        var keywordGroups: ArrayList<KeywordGroups> ? = null
            private set
        var device: String ? = null
            private set
        var ages: ArrayList<String> ? = null
            private set
        var gender: String ? = null
            private set

        fun startDate(date: String) = apply { this.startDate = date}
        fun endDate(date: String) = apply { this.endDate = date }
        fun timeUnit(unit: String) = apply { this.timeUnit = unit }
        fun keywordGroups(group: ArrayList<KeywordGroups>) = apply { this.keywordGroups = group }
        fun device(device: String) = apply { this.device = device }
        fun ages(ages: ArrayList<String>) = apply { this.ages = ages }
        fun gender(gender: String) = apply { this.gender = gender }

        fun build() = SearchTrend(this)
    }

}
