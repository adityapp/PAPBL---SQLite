package com.axce.sqlite.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.axce.sqlite.R
import com.axce.sqlite.adapter.ProdukAdapter
import com.axce.sqlite.dbHelper.ProdukDatabaseHelper
import com.axce.sqlite.model.ProdukModel
import com.axce.sqlite.model.TokoModel
import com.axce.sqlite.utils.ProdukDialogBox
import com.axce.sqlite.utils.ProdukUpdateDialogBox
import kotlinx.android.synthetic.main.activity_produk.*


class ProdukActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toko: TokoModel
    private lateinit var dialogBox: ProdukDialogBox
    private lateinit var produkHelper: ProdukDatabaseHelper
    private lateinit var adapter: ProdukAdapter
    private lateinit var dialogBoxUpdate: ProdukUpdateDialogBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)

        toko = intent.getSerializableExtra("TOKO") as TokoModel
        title_bar.text = toko.nama

        produkHelper = ProdukDatabaseHelper(this)
        dialogBox = ProdukDialogBox()
        dialogBoxUpdate = ProdukUpdateDialogBox()

        adapter = ProdukAdapter(this)
        adapter.dataSet = produkHelper.getAllData(toko.id)
        rv_toko.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_toko.adapter = adapter

        edt_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.d("TEXT", p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("TEXT", p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    adapter.dataSet = produkHelper.filter(toko.id, p0!!)
                } else {
                    adapter.dataSet = produkHelper.getAllData(toko.id)
                }
                adapter.notifyDataSetChanged()
            }

        })

        adapter.setOnClickListener(object : ProdukAdapter.OnClickListener {
            override fun delete(data: ProdukModel) {
                produkHelper.delete(data)
                adapter.dataSet = produkHelper.getAllData(toko.id)
                adapter.notifyDataSetChanged()
            }

            override fun update(data: ProdukModel) {
                dialogBoxUpdate.showNoticeDialog(this@ProdukActivity, toko.id, produkHelper, adapter, data)
            }
        })

        btn_add.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn_add -> dialogBox.showNoticeDialog(this, toko.id, produkHelper, adapter)
        }
    }

    override fun onDestroy() {
        produkHelper.close()
        super.onDestroy()
    }
}
