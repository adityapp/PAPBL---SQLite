package com.axce.sqlite.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.axce.sqlite.R
import com.axce.sqlite.adapter.TokoAdapter
import com.axce.sqlite.dbHelper.TokoDatabaseHelper
import com.axce.sqlite.model.TokoModel
import com.axce.sqlite.utils.TokoDialogBox
import com.axce.sqlite.utils.TokoUpdateDialogBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dialogBox: TokoDialogBox
    private lateinit var tokoHelper: TokoDatabaseHelper
    private lateinit var adapter: TokoAdapter
    private lateinit var dialogBoxUpdate: TokoUpdateDialogBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tokoHelper = TokoDatabaseHelper(this)
        dialogBox = TokoDialogBox()
        dialogBoxUpdate = TokoUpdateDialogBox()

        adapter = TokoAdapter(this)
        adapter.dataSet = tokoHelper.getAllData()
        rv_toko.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_toko.adapter = adapter

        adapter.setOnClickListener(object : TokoAdapter.OnClickListener{
            override fun delete(data: TokoModel) {
                tokoHelper.delete(data)
                adapter.dataSet = tokoHelper.getAllData()
                adapter.notifyDataSetChanged()
            }

            override fun update(data: TokoModel) {
                dialogBoxUpdate.showNoticeDialog(this@MainActivity, tokoHelper, adapter, data)
            }
        })

        btn_add.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btn_add -> dialogBox.showNoticeDialog(this, tokoHelper, adapter)
        }
    }

    override fun onDestroy() {
        tokoHelper.close()
        super.onDestroy()
    }
}
