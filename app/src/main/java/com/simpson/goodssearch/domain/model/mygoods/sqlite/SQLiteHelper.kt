package com.simpson.goodssearch.domain.model.mygoods.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        const val id = "id"
        const val table_name = "my_goods"
        const val goods_name = "goods_name"
        const val goods_url = "goods_url"
        const val image_url = "image_url"
        const val mall_name = "mall_name"
        const val low_price = "lprice"
        const val high_price = "hprice"
        const val goods_date = "goods_date"
        const val goods_id = "goods_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "create table if not exists $table_name ( " +
                "    $id integer primary key autoincrement, " +
                "    $goods_name text not null, " +
                "    $goods_url text, " +
                "    $goods_id int," +
                "    $image_url text, " +
                "    $mall_name text, " +
                "    $low_price int, " +
                "    $high_price int, " +
                "    $goods_date date);"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropQuery = "drop table $table_name;"
        db?.execSQL(dropQuery)
        onCreate(db)
    }
}