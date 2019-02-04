package com.axce.sqlite.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.COLUMN_NAME_ALAMAT_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.COLUMN_NAME_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.TABLE_NAME_TOKO
import com.axce.sqlite.model.TokoModel

class TokoDatabaseHelper(context: Context) {
    private var helper: DatabaseHelper
    private var database: SQLiteDatabase

    init {
        helper = DatabaseHelper(context)
        database = helper.writableDatabase
    }

    fun insert(toko: TokoModel){
        val value = ContentValues().apply {
            put(COLUMN_NAME_TOKO, toko.nama)
            put(COLUMN_NAME_ALAMAT_TOKO, toko.alamat)
        }

        database.insert(TABLE_NAME_TOKO, null, value)
    }

    fun update(toko: TokoModel){
        val value = ContentValues().apply {
            put(COLUMN_NAME_TOKO, toko.nama)
            put(COLUMN_NAME_ALAMAT_TOKO, toko.alamat)
        }

        val selection = "$_ID LIKE ${toko.id}"
        database.update(TABLE_NAME_TOKO, value, selection, null)
    }

    fun delete(toko: TokoModel){
        val selection = "$_ID LIKE ${toko.id}"
        database.delete(TABLE_NAME_TOKO, selection, null)
    }

    fun getAllData(): ArrayList<TokoModel>{
        var arrayData = arrayListOf<TokoModel>()
        val cursor = database.query(TABLE_NAME_TOKO,null,null,null,null,null, "$_ID ASC", null)
        cursor.moveToFirst()
        if (cursor.count > 0){
            do {
                var toko = TokoModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TOKO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ALAMAT_TOKO))
                )
                arrayData.add(toko)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayData
    }

    fun close(){
        database.close()
    }
}
