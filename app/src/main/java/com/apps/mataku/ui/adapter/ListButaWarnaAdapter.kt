package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.LogButaWarna
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_konsultasi.view.*


import java.util.*


/**
 * Created by root on 11/15/17.
 */

class ListButaWarnaAdapter(private val context: Context?) : RecyclerView.Adapter<ListButaWarnaAdapter.ViewHolder>(){
 private var listButaWarna: List<LogButaWarna> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listButaWarna[position])
   }

   override fun getItemCount(): Int {
       return listButaWarna.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_log, parent, false))
       return ViewHolder(root)
   }

   fun addListButaWarna(listButaWarna: List<LogButaWarna>?) {
       if (listButaWarna != null) {
           this.listButaWarna = listButaWarna
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(listButaWarna: LogButaWarna) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(listButaWarna.date_created.toLong() * 1000))
           ui_nama.text=date
           ui_status.text=listButaWarna.hasil
           Glide.with(this).load(R.drawable.ic_buta_warna).into(ui_foto)
       }
   }
}