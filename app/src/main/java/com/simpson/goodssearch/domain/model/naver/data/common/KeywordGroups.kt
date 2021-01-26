package com.simpson.goodssearch.domain.model.naver.data.common

data class KeywordGroups(val groupName: String, val keywords: ArrayList<String>){
    constructor(builder: Builder) :
            this(builder.groupName!!, builder.keywords!!)

    class Builder {
        var groupName: String ? = null
        var keywords: ArrayList<String> ? = null

        fun groupName(name: String) = apply { this.groupName = name }
        fun keywords(keys: ArrayList<String>) = apply { this.keywords = keys }

        fun build() = KeywordGroups(this)
    }

}