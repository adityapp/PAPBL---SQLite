package com.axce.sqlite.model

import java.io.Serializable

data class TokoModel(
        var id: Int,
        var nama: String = "",
        var alamat: String = ""
):Serializable{
    constructor(nama: String, alamat: String):this(0,nama, alamat)
}