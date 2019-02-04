package com.axce.sqlite.dbHelper

import android.provider.BaseColumns

object DatabaseContract {
    object Toko : BaseColumns {
        const val TABLE_NAME_TOKO = "toko"
        const val COLUMN_NAME_TOKO = "nama"
        const val COLUMN_NAME_ALAMAT_TOKO = "alamat"
    }

    object Produk : BaseColumns {
        const val TABLE_NAME_PRODUK = "produk"
        const val COLUMN_NAME_PRODUK = "nama"
        const val COLUMN_NAME_HARGA = "harga"
        const val COLUMN_NAME_ID_TOKO = "id_toko"
    }
}