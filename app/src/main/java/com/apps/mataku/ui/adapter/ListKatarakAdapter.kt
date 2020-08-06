package com.hmik_up.smarthima.ui.adapter

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.LogKatarak
import com.bumptech.glide.Glide

import java.util.*
import kotlinx.android.synthetic.main.list_log.view.*


/**
 * Created by root on 11/15/17.
 */

class ListKatarakAdapter(private val context: Context?) : RecyclerView.Adapter<ListKatarakAdapter.ViewHolder>(){
 private var listKatarak: List<LogKatarak> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listKatarak[position])
   }

   override fun getItemCount(): Int {
       return listKatarak.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_log, parent, false))
       return ViewHolder(root)
   }

   fun addListKatarak(listKatarak: List<LogKatarak>?) {
       if (listKatarak != null) {
           this.listKatarak = listKatarak
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(listKatarak: LogKatarak) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(listKatarak.date_created.toLong() * 1000))
           ui_nama.text=date
           ui_status.text=listKatarak.hasil
           Glide.with(this).load(R.drawable.ic_katarak).into(ui_foto)
       }
   }
}