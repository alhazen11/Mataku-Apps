package com.apps.mataku.ui.minus

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MinusPresenterImpl(val interactor: MinusInteractor, private var view: MinusView?): MinusPresenter{
    override fun setViewMinus(minusView: MinusView, data: Map<String, String>) {
        view = minusView
        getMinus(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getMinus(data: Map<String, String>){
        interactor.addMinus(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetMinus(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetMinus(dataResponse: SuccesResponse) {
        view?.showMinus(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }

}