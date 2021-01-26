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
        const val table_name = "my_goods"
        const val goods_name = "name"
        const val goods_url = "url"
        const val image_url = "image_url"
        const val mall_name = "mall"
        const val low_price = "lprice"
        const val high_price = "hprice"
        const val date = "date"
        const val goods_id = "id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "create table if not exists $table_name ($goods_name text not null, " +
                "    $goods_url text, " +
                "    $goods_id int primary key," +
                "    $image_url text, " +
                "    $mall_name text, " +
                "    $low_price int, " +
                "    $high_price int, " +
                "    $date date);"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropQuery = "drop table $table_name;"
        db?.execSQL(dropQuery)
        onCreate(db)
    }
}