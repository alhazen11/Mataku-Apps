package com.apps.mataku.ui.butawarna

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ButaWarnaPresenterImpl(val interactor: ButaWarnaInteractor, private var view: ButaWarnaView?): ButaWarnaPresenter{
    override fun setViewButaWarna(butaWarnaView: ButaWarnaView, data: Map<String, String>) {
        view = butaWarnaView
        getButaWarna(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getButaWarna(data: Map<String, String>){
        interactor.addButaWarna(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetButaWarna(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun onGetButaWarna(dataResponse: SuccesResponse?) {
        view?.showButaWarna(dataResponse?.status, dataResponse?.error!!,dataResponse?.result)
    }
}