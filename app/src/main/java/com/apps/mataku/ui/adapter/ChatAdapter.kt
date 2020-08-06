package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Message
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.list_konsultasi.view.*


/**
 * Created by root on 11/15/17.
 */

class ChatAdapter(private val context: Context?) : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){
 private var message: List<Message> = ArrayList()
    private var userNama: String? = null
    private var userFoto: String? = null
    private var username: String? = null
    private var fotoDokter: String? = null
    private var namaDokter: String? = null
    private var userDokterID: String? = null
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(message[position])
   }

   override fun getItemCount(): Int {
       return message.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_chat, parent, false))
       return ViewHolder(root)
   }

   fun addMessage(message: List<Message>?,username:String,userFoto:String,userNama:String,userDokterID:String,fotoDokter:String,namaDokter:String) {
       if (message != null) {
           this.message = message
           this.userDokterID =userDokterID
           this.fotoDokter =fotoDokter
           this.namaDokter =namaDokter
           this.username=username
           this.userNama=userNama
           this.userFoto=userFoto
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(message: Message) = with(itemView) {
           var requestOptions = RequestOptions()
           requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(message.timestamp.toLong() * 1000))

           if(message.userID==username){
               ui_status.text=message.message
               ui_nama.text=userNama+" - "+date
               Glide.with(this).load(userFoto).apply(requestOptions).into(ui_foto)
           }else{
               ui_status.text=message.message
               ui_nama.text=namaDokter+" - "+date
               Glide.with(this).load(fotoDokter).apply(requestOptions).into(ui_foto)

           }
       }
   }
}