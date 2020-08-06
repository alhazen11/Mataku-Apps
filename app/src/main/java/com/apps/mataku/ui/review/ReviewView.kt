package com.apps.mataku.ui.review

import com.apps.mataku.models.Review


interface ReviewView {
    fun showReview(status: String?, error: Boolean,result: List<Review>?)
    fun showReviewDokter(status: String?, error: Boolean,result: List<Review>?)
}