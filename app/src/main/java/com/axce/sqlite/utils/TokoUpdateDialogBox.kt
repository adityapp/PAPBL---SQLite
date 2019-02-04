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
import com.axce.sqlite.adapter.TokoAdapter
import com.axce.sqlite.dbHelper.TokoDatabaseHelper
import com.axce.sqlite.model.TokoModel
import kotlinx.android.synthetic.main.dialog_box_toko_update.*

class TokoUpdateDialogBox : DialogFragment() {
    fun showNoticeDialog(context: Context, tokoHelper: TokoDatabaseHelper, adapter: TokoAdapter, data: TokoModel) {
        val activity = context as Activity
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_box_toko_update)

        dialog.edt_nama.setText(data.nama)
        dialog.edt_alamat.setText(data.alamat)

        dialog.btn_batal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.btn_update.setOnClickListener {
            if (!TextUtils.isEmpty(dialog.edt_nama.text) && !TextUtils.isEmpty(dialog.edt_alamat.text)){
                data.nama = dialog.edt_nama.text.toString()
                data.alamat = dialog.edt_alamat.text.toString()

                tokoHelper.update(data)
                adapter.dataSet = tokoHelper.getAllData()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(context, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
