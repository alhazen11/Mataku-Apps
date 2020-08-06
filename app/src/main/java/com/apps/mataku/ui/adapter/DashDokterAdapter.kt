package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Member
import com.apps.mataku.ui.dashboard.DashClickInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.list_dokter_one.view.*


/**
 * Created by root on 11/15/17.
 */

class DashDokterAdapter(private val context: Context?) : RecyclerView.Adapter<DashDokterAdapter.ViewHolder>(){
    private var member: List<Member> = ArrayList()
    private var listner: DashClickInterface? = null


   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(member[position])
   }

   override fun getItemCount(): Int {
       return member.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_dokter_one, parent, false))
       return ViewHolder(root)
   }

   fun addMember(member: List<Member>?,listner:DashClickInterface) {
       if (member != null) {
           this.member = member
           this.listner=listner;
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
           itemView?.setOnClickListener {
               if (adapterPosition != RecyclerView.NO_POSITION) {
                   listner?.recyclerViewMengajukanOnClick(member[adapterPosition].id,member[adapterPosition].nama,member[adapterPosition].getFotoUrl())
               }
           }
       }

       fun bind(member: Member) = with(itemView) {
           ui_nama.text=member.nama
           var requestOptions = RequestOptions()
           requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
           Glide.with(this).load(member.getFotoUrl()).apply(requestOptions).into(ui_foto)
       }
   }
}