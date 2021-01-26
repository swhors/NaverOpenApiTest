package com.simpson.goodssearch.domain.model.mygoods.sqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.*
import android.util.Log
import kotlinx.android.synthetic.main.goods_list_view.view.*
import kotlin.collections.ArrayList


class SQLiteCtl() {
    private lateinit var _helper: SQLiteHelper
    private lateinit var _sqlite: SQLiteDatabase

    private val _tag = "my_goods"

    constructor(helper: SQLiteHelper) : this() {
        this._helper = helper
    }

    fun insert(goods_name: String,
               goods_id: Int,
               goods_url: String,
               image_url: String,
               mall_name: String,
               lprice: Int,
               hprice: Int) {
        val sqlite = _helper.writableDatabase
        val values = ContentValues()
        values.put(SQLiteHelper.goods_name, goods_name)
        values.put(SQLiteHelper.goods_id, goods_id)
        values.put(SQLiteHelper.goods_url, goods_url)
        values.put(SQLiteHelper.image_url, image_url)
        values.put(SQLiteHelper.mall_name, mall_name)
        values.put(SQLiteHelper.low_price, lprice)
        values.put(SQLiteHelper.high_price, hprice)
        val date = Date().time
        values.put(SQLiteHelper.date, date)
        sqlite.insert(SQLiteHelper.table_name, null, values)
    }

    fun select(): ArrayList<String> {
        val sqlite = _helper.readableDatabase
        val c: Cursor = sqlite.query(SQLiteHelper.table_name, null, null, null, null, null, null)
        val columns = arrayListOf(SQLiteHelper.goods_name, SQLiteHelper.goods_id,
            SQLiteHelper.goods_url, SQLiteHelper.image_url, SQLiteHelper.mall_name,
            SQLiteHelper.low_price, SQLiteHelper.high_price, SQLiteHelper.date)
        val returnValue = ArrayList<String>(columns.size)
        while(c.moveToNext()) {
            var cnt = 0
            do {
                returnValue[cnt] = c.getString(c.getColumnIndex(columns[cnt]))
                Log.i("$_tag select :", "$cnt - ${returnValue[cnt]}")
            } while(cnt++ < returnValue.size)
        }
        c.close()
        return returnValue
    }

    fun select(goods_id: Int): ArrayList<MyGoods>{
        val sqlite = _helper.writableDatabase
        var query: String ?= null
        if (goods_id == -1) {
            query = "select * from ${SQLiteHelper.table_name}"
        } else {
            query = "select * from ${SQLiteHelper.table_name} where ${SQLiteHelper.goods_id} = ${goods_id};"
        }
        val cursor: Cursor = sqlite.rawQuery(query, null)
        val columns = arrayListOf(SQLiteHelper.goods_name, SQLiteHelper.goods_id,
            SQLiteHelper.goods_url, SQLiteHelper.image_url, SQLiteHelper.mall_name,
            SQLiteHelper.low_price, SQLiteHelper.high_price, SQLiteHelper.date)

        val results = ArrayList<MyGoods>()
        while(cursor.moveToNext()) {
            val myGoods = MyGoods()
            var cnt = 0
            do {
                var returnVal = cursor.getString(cursor.getColumnIndex(columns[cnt]))
                when(columns[cnt]) {
                    SQLiteHelper.goods_name -> myGoods.name = returnVal
                    SQLiteHelper.goods_id -> myGoods.id = returnVal.toLong()
                    SQLiteHelper.goods_url -> myGoods.url = returnVal
                    SQLiteHelper.image_url -> myGoods.image = returnVal
                    SQLiteHelper.mall_name -> myGoods.mall = returnVal
                    SQLiteHelper.high_price -> myGoods.hprice = returnVal.toInt()
                    SQLiteHelper.low_price -> myGoods.lprice = returnVal.toInt()
                    SQLiteHelper.date -> myGoods.date = returnVal.toLong()
                }
                Log.i("$_tag select", "$cnt/${columns[cnt]} - ${returnVal}")
            } while(++cnt < columns.size)
            results.add(myGoods)
            Log.i("$_tag nect.......", "")
        }
        cursor.close()
        return results
    }

    fun update(goods_id: Int, lprice: Int, hprice: Int  ) {
        val sqlite = _helper.writableDatabase
        val query = "update ${SQLiteHelper.table_name} set ${SQLiteHelper.low_price}=$lprice, "
                "        ${SQLiteHelper.high_price}=$hprice " +
                "    where ${SQLiteHelper.goods_id}=${goods_id};"
        sqlite.execSQL(query)
    }

    fun delete(goods_id: Int) {
        val sqlite = _helper.writableDatabase
        sqlite.delete(SQLiteHelper.table_name, "${SQLiteHelper.goods_id}=?", arrayOf(goods_id.toString()))
    }

    fun dbclose() {
        _sqlite.close()
        _helper.close()
    }
}
