package com.axce.sqlite.dbHelper

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import android.util.Log
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_HARGA
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_ID_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.COLUMN_NAME_PRODUK
import com.axce.sqlite.dbHelper.DatabaseContract.Produk.TABLE_NAME_PRODUK
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.COLUMN_NAME_ALAMAT_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.COLUMN_NAME_TOKO
import com.axce.sqlite.dbHelper.DatabaseContract.Toko.TABLE_NAME_TOKO

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "TokoOnline.db"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
                "CREATE TABLE $TABLE_NAME_TOKO (" +
                        "$_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_NAME_TOKO TEXT NOT NULL, " +
                        "$COLUMN_NAME_ALAMAT_TOKO TEXT NOT NULL);"
        )

        p0?.execSQL(
                "CREATE TABLE $TABLE_NAME_PRODUK (" +
                        "$_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_NAME_PRODUK TEXT NOT NULL, " +
                        "$COLUMN_NAME_HARGA TEXT NOT NULL, " +
                        "$COLUMN_NAME_ID_TOKO INTEGER, " +
                        "FOREIGN KEY ($COLUMN_NAME_ID_TOKO) REFERENCES $TABLE_NAME_TOKO($_ID));"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME_TOKO}")
        p0?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME_PRODUK}")
        onCreate(p0)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}