package com.simpson.goodssearch.domain.model.naver.data.common

enum class ProductId {
    normal_comp_price(1),
    normal_unmatched(2),
    normal_matched(3),
    old_comp_price(4),
    old_unmatched(5),
    old_matched(6),
    soldout_comp_price(7),
    soldout_unmatched(8),
    soldout_matched(9),
    ready_comp_price(10),
    ready_comp_unmatched(11),
    ready_comp_matched(12);

    var pid: Int

    constructor(pid: Int) {this.pid = pid}
    fun productId() = pid
}