package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Notifikasi
import com.apps.mataku.ui.favorit.FavoritActivity
import com.apps.mataku.ui.konsultasi.KonsultasiActivity
import com.apps.mataku.ui.review.ReviewActivity



import java.util.*
import kotlinx.android.synthetic.main.list_notifikasi.view.*


/**
 * Created by root on 11/15/17.
 */

class NotifikasiAdapter(private val context: Context?) : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>(){
 private var notifikasi: List<Notifikasi> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(notifikasi[position])
   }

   override fun getItemCount(): Int {
       return notifikasi.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_notifikasi, parent, false))
       return ViewHolder(root)
   }

   fun addNotifikasi(pengajuan: List<Notifikasi>?) {
       if (pengajuan != null) {
           this.notifikasi = pengajuan
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
           itemView?.setOnClickListener {
               if (adapterPosition != RecyclerView.NO_POSITION) {
                   if(notifikasi[adapterPosition].activity=="Favorit"){
                       val intent = Intent(context, FavoritActivity::class.java)
                       context?.let { it1 -> startActivity(it1, intent, null) }
                   }else if(notifikasi[adapterPosition].activity=="Konsultasi"){
                       val intent = Intent(context, KonsultasiActivity::class.java)
                       context?.let { it1 -> startActivity(it1, intent, null) }
                   }else if(notifikasi[adapterPosition].activity=="Review"){
                       val intent = Intent(context, ReviewActivity::class.java)
                       context?.let { it1 -> startActivity(it1, intent, null) }
                   }
               }
           }
       }

       fun bind(notifikasi: Notifikasi) = with(itemView) {
           ui_nama.text=notifikasi.activity
           ui_status.text=notifikasi.keterangan
       }
   }
}