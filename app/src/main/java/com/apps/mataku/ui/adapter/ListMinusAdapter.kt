package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.LogMinus
import com.bumptech.glide.Glide


import java.util.*
import kotlinx.android.synthetic.main.list_log.view.*


/**
 * Created by root on 11/15/17.
 */

class ListMinusAdapter(private val context: Context?) : RecyclerView.Adapter<ListMinusAdapter.ViewHolder>(){
 private var listMinus: List<LogMinus> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listMinus[position])
   }

   override fun getItemCount(): Int {
       return listMinus.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_log, parent, false))
       return ViewHolder(root)
   }

   fun addPengajuan(listMinus: List<LogMinus>?) {
       if (listMinus != null) {
           this.listMinus = listMinus
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(listMinus: LogMinus) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(listMinus.date_created.toLong() * 1000))
           ui_nama.text=date
           ui_status.text=listMinus.hasil
           Glide.with(this).load(R.drawable.ic_miopi).into(ui_foto)
       }
   }
}