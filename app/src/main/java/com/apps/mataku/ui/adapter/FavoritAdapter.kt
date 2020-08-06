package com.hmik_up.smarthima.ui.adapter

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Favorit
import com.apps.mataku.ui.favorit.FavoritClickInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.list_favorit.view.*


/**
 * Created by root on 11/15/17.
 */

class FavoritAdapter(private val context: Context?) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>(){
 private var favorit: List<Favorit> = ArrayList()
    private var listner: FavoritClickInterface? = null

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(favorit[position])
   }

   override fun getItemCount(): Int {
       return favorit.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_favorit, parent, false))
       return ViewHolder(root)
   }

   fun addFavorit(favorit: List<Favorit>?, listner: FavoritClickInterface) {
       if (favorit != null) {
           this.favorit = favorit
           this.listner=listner;
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
           if (adapterPosition != RecyclerView.NO_POSITION) {
               listner?.recyclerViewHapus(favorit[adapterPosition].id.toString(),favorit[adapterPosition].user.nama,favorit[adapterPosition].user.getFotoUrl())
           }
       }

       fun bind(favorit: Favorit) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(favorit.date_created.toLong() * 1000))
           ui_nama.text=favorit.user.nama+" - "+date
           var requestOptions = RequestOptions()
           requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
           Glide.with(this).load(favorit.user.getFotoUrl()).apply(requestOptions).into(ui_foto)
       }
   }
}