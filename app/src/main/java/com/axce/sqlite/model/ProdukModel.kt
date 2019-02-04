package com.axce.sqlite.model

import java.io.Serializable

data class ProdukModel(
        var id: Int,
        var nama: String = "",
        var harga: String = "",
        var id_toko: Int
) : Serializable{
    constructor(nama: String, harga: String, id_toko: Int):this(0, nama, harga, id_toko)
}