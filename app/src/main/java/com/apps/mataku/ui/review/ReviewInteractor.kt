package com.apps.mataku.ui.review


import com.apps.mataku.api.ReviewResponse
import rx.Observable

interface ReviewInteractor {
    fun getReview(data:Map<String, String>): Observable<ReviewResponse>
    fun getDokterReview(data:Map<String, String>): Observable<ReviewResponse>
}