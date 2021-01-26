package com.simpson.goodssearch.domain.model.kakao.response

data class WebSearchResponse (
    var documents:ArrayList<Document>,
    var meta:Meta
) {
    data class Document(
            var title:String,
            var content: String,
            var url: String,
            var datetime: String)

    data class Meta(
        var total_count:Int,
        var pageable_count:Int,
        var is_end:Boolean
    )
}