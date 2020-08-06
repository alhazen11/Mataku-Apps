package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.LogPterigium
import com.bumptech.glide.Glide

import java.util.*
import kotlinx.android.synthetic.main.list_log.view.*


/**
 * Created by root on 11/15/17.
 */

class ListPterigiumAdapter(private val context: Context?) : RecyclerView.Adapter<ListPterigiumAdapter.ViewHolder>(){
 private var listPterigium: List<LogPterigium> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listPterigium[position])
   }

   override fun getItemCount(): Int {
       return listPterigium.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_log, parent, false))
       return ViewHolder(root)
   }

   fun addListPterigium(listPterigium: List<LogPterigium>?) {
       if (listPterigium != null) {
           this.listPterigium = listPterigium
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(listPterigium: LogPterigium) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(listPterigium.date_created.toLong() * 1000))
           ui_nama.text=date
           ui_status.text=listPterigium.hasil
           Glide.with(this).load(R.drawable.ic_eye_minus).into(ui_foto)
       }
   }
}