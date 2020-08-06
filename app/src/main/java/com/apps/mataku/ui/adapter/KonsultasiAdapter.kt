package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Konsultasi
import com.apps.mataku.ui.chat.ChatActivity
import com.apps.mataku.ui.konsultasi.KonsultasiClickInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_konsultasi.view.*


import java.util.*


/**
 * Created by root on 11/15/17.
 */

class KonsultasiAdapter(private val context: Context?) : RecyclerView.Adapter<KonsultasiAdapter.ViewHolder>(){
    private var konsultasi: List<Konsultasi> = ArrayList()
    private var listner: KonsultasiClickInterface? = null
    private var isDokter:String=""

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(konsultasi[position])
   }

   override fun getItemCount(): Int {
       return konsultasi.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_konsultasi, parent, false))
       return ViewHolder(root)
   }

   fun addKonsultasi(konsultasi: List<Konsultasi>?,listner: KonsultasiClickInterface,isDokter:String) {
       if (konsultasi != null) {
           this.konsultasi = konsultasi
           this.listner=listner;
           this.isDokter=isDokter
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
           itemView?.setOnClickListener {
               if (adapterPosition != RecyclerView.NO_POSITION) {
                   if(konsultasi[adapterPosition].aktif=="1"){
                   val intent = Intent(context, ChatActivity::class.java)
                   intent.putExtra("idUser", konsultasi[adapterPosition].id_user)
                   intent.putExtra("idDokter", konsultasi[adapterPosition].id_user_dokter)
                   intent.putExtra("fotoDokter", konsultasi[adapterPosition].user.getFotoUrl())
                   intent.putExtra("namaDokter", konsultasi[adapterPosition].user.nama)
                   context?.let { it1 ->
                       startActivity(it1, intent, null)
                   }
                   }
               }
           }
       }

       fun bind(konsultasi: Konsultasi) = with(itemView) {
           if(konsultasi.aktif=="1"){
               ui_status.text="Tersambung"
           }else{
               ui_status.text="Menungggu konfrimasi"
           }
           if(isDokter!="1"){
               ui_review.text="Review"
           }else{
               ui_review.text="Terima"
           }
           ui_review.setOnClickListener {
               if(isDokter!="1"){
                   listner?.recyclerViewReview(konsultasi.id_user_dokter,konsultasi.user.nama,konsultasi.user.getFotoUrl())
               }else{
                   listner?.recyclerViewReview(konsultasi.id.toString(),konsultasi.user.nama,konsultasi.user.getFotoUrl())
               }
           }
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(konsultasi.date_created.toLong() * 1000))

           ui_nama.text=konsultasi.user.nama+" - "+date
           var requestOptions = RequestOptions()
           requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
           Glide.with(this).load(konsultasi.user.getFotoUrl()).apply(requestOptions).into(ui_foto)
       }
   }
}