package com.simpson.goodssearch.domain.model.naver.data.common

enum class Sort {
    SIM("sim"), DATE("date"), ASC("asc"), DSC("dsc");
    private var sortType: String
    constructor(sort: String) {this.sortType = sort}

    fun sort(): String = this.sortType
}