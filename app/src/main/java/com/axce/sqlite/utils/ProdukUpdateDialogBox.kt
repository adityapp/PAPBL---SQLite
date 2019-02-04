package com.axce.sqlite.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import com.axce.sqlite.R
import com.axce.sqlite.adapter.ProdukAdapter
import com.axce.sqlite.dbHelper.ProdukDatabaseHelper
import com.axce.sqlite.model.ProdukModel
import kotlinx.android.synthetic.main.dialog_box_produk_update.*

class ProdukUpdateDialogBox : DialogFragment() {
    fun showNoticeDialog(context: Context, idToko: Int, produkHelper: ProdukDatabaseHelper, adapter: ProdukAdapter, data: ProdukModel) {
        val activity = context as Activity
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_box_produk_update)

        dialog.edt_nama.setText(data.nama)
        dialog.edt_harga.setText(data.harga)

        dialog.btn_batal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.btn_update.setOnClickListener {
            if (!TextUtils.isEmpty(dialog.edt_nama.text) && !TextUtils.isEmpty(dialog.edt_harga.text)){
                data.nama = dialog.edt_nama.text.toString()
                data.harga = dialog.edt_harga.text.toString()

                produkHelper.update(data)
                adapter.dataSet = produkHelper.getAllData(idToko)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(context, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
