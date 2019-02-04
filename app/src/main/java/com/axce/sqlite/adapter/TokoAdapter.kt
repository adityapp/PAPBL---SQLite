package com.axce.sqlite.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.axce.sqlite.R
import com.axce.sqlite.activity.ProdukActivity
import com.axce.sqlite.model.TokoModel
import kotlinx.android.synthetic.main.card_toko.view.*

class TokoAdapter(val context: Context) : RecyclerView.Adapter<TokoAdapter.ViewHolder>() {
    var dataSet = arrayListOf<TokoModel>()
    private lateinit var onClickItemListener: OnClickListener

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.card_toko, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txt_nama.text = dataSet[position].nama
        holder.itemView.txt_alamat.text = dataSet[position].alamat
        holder.itemView.card_toko.setOnClickListener {
            Log.d("ID", dataSet[position].id.toString())
            val intent = Intent(context, ProdukActivity::class.java)
            intent.putExtra("TOKO", dataSet[position])
            context.startActivity(intent)
        }
        holder.itemView.btn_hapus.setOnClickListener {
            onClickItemListener.delete(dataSet[position])
        }
        holder.itemView.btn_ubah.setOnClickListener {
            onClickItemListener.update(dataSet[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setOnClickListener(listener: OnClickListener){
        onClickItemListener = listener
    }

    interface OnClickListener{
        fun delete(data: TokoModel)
        fun update(data: TokoModel)
    }
}