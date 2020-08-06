package com.apps.mataku.ui.review

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.ReviewResponse
import rx.Observable

class ReviewInteractorImpl(private val dataDbApi: DataDbApi):ReviewInteractor{
    override fun getReview(data: Map<String, String>): Observable<ReviewResponse> {
        return dataDbApi.getReview(data)
    }

    override fun getDokterReview(data: Map<String, String>): Observable<ReviewResponse> {
        return dataDbApi.getReviewDokter(data)
    }

}