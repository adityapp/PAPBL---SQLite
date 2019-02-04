package com.axce.sqlite.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns._ID
import android.util.Log
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_HARGA
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_ID_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_PRODUK
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.TABLE_NAME_PRODUK
import com.axce.sqlite.model.ProdukModel

class ProdukDatabaseHelper(context: Context) {
    private var helper: DatabaseHelper
    private var database: SQLiteDatabase

    init {
        helper = DatabaseHelper(context)
        database = helper.writableDatabase
    }

    fun insert(produk: ProdukModel) {
        val value = ContentValues().apply {
            put(COLUMN_NAME_PRODUK, produk.nama)
            put(COLUMN_NAME_HARGA, produk.harga)
            put(COLUMN_NAME_ID_TOKO, produk.id_toko)
        }

        database.insert(TABLE_NAME_PRODUK, null, value)
    }

    fun update(produk: ProdukModel) {
        val value = ContentValues().apply {
            put(COLUMN_NAME_PRODUK, produk.nama)
            put(COLUMN_NAME_HARGA, produk.harga)
            put(COLUMN_NAME_ID_TOKO, produk.id_toko)
        }

        val selection = "$_ID LIKE ${produk.id}"
        database.update(TABLE_NAME_PRODUK, value, selection, null)
    }

    fun delete(produk: ProdukModel) {
        val selection = "$_ID LIKE ${produk.id}"
        database.delete(TABLE_NAME_PRODUK, selection, null)
    }

    fun getAllData(tokoId: Int): ArrayList<ProdukModel> {
        var arrayData = arrayListOf<ProdukModel>()
        Log.e("TOKOID", tokoId.toString())
        val selection = "$COLUMN_NAME_ID_TOKO = $tokoId"
        val cursor = database.query(TABLE_NAME_PRODUK, null, selection, null, null, null, "$_ID ASC", null)
        cursor.moveToFirst()
        if (cursor.count > 0) {
            do {
                var produk = ProdukModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow(_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRODUK)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HARGA)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ID_TOKO))
                )
                arrayData.add(produk)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayData
    }

    fun close() {
        database.close()
    }
}
