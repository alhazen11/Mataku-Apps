package com.apps.mataku.ui.review

import okhttp3.RequestBody

interface ReviewPresenter {
    fun setViewGetReview(reviewtView: ReviewView,  data:Map<String, String>)
    fun setViewGetReviewDokter(reviewtView: ReviewView,  data:Map<String, String>)

}