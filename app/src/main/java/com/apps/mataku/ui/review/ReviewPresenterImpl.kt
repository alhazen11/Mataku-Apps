package com.apps.mataku.ui.review

import android.util.Log
import com.apps.mataku.api.ReviewResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ReviewPresenterImpl(val interactor: ReviewInteractor, private var view: ReviewView?): ReviewPresenter{
    override fun setViewGetReview(reviewtView: ReviewView, data: Map<String, String>) {
        view = reviewtView
        getReview(data)
    }

    override fun setViewGetReviewDokter(reviewtView: ReviewView, data: Map<String, String>) {
        view = reviewtView
        getReviewDokter(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }
    private fun getReviewDokter(data: Map<String, String>){
        interactor.getDokterReview(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { ReviewResponse -> onGetReviewDokter(ReviewResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getReview(data: Map<String, String>){
        interactor.getReview(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { ReviewResponse -> onGetReview(ReviewResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetReview(dataResponse: ReviewResponse) {
        view?.showReview(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
    private fun onGetReviewDokter(dataResponse: ReviewResponse?) {
        view?.showReviewDokter(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}