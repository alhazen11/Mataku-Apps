package com.hmik_up.smarthima.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.mataku.R
import com.apps.mataku.models.Review
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.list_review.view.*


/**
 * Created by root on 11/15/17.
 */

class ReviewAdapter(private val context: Context?) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){
 private var review: List<Review> = ArrayList()

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(review[position])
   }

   override fun getItemCount(): Int {
       return review.size
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val root = (LayoutInflater.from(parent.context).inflate(R.layout.list_review, parent, false))
       return ViewHolder(root)
   }

   fun addReview(review: List<Review>?) {
       if (review != null) {
           this.review = review
           notifyDataSetChanged()
       }
   }

   inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
       init {
       }

       fun bind(review: Review) = with(itemView) {
           val date = java.text.SimpleDateFormat("MMM dd yyyy - HH:mm a").format(java.util.Date(review.date_created.toLong() * 1000))
           ui_start1
           ui_keterangan
           if(review.rate=="1"){
               Glide.with(this).load(R.drawable.ic_star).into(ui_start1)
           }else if(review.rate=="2"){
               Glide.with(this).load(R.drawable.ic_star).into(ui_start1)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start2)
           }else if(review.rate=="3"){
               Glide.with(this).load(R.drawable.ic_star).into(ui_start1)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start2)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start3)
           }else if(review.rate=="4"){
               Glide.with(this).load(R.drawable.ic_star).into(ui_start1)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start2)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start3)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start4)
           }else{
               Glide.with(this).load(R.drawable.ic_star).into(ui_start1)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start2)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start3)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start4)
               Glide.with(this).load(R.drawable.ic_star).into(ui_start5)
           }
           ui_nama.text=review.user.nama+" - "+date
           ui_keterangan.text=review.keterangan
           var requestOptions = RequestOptions()
           requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
           Glide.with(this).load(review.user.getFotoUrl()).apply(requestOptions).into(ui_foto)
       }
   }
}