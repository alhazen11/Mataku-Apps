package com.apps.mataku.ui.katarak

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class KatarakPresenterImpl(val interactor: KatarakInteractor, private var view: KatarakView?): KatarakPresenter{
    override fun setViewKatarak(katarakView: KatarakView, data: Map<String, String>) {
        view = katarakView
        getKatarak(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getKatarak(data: Map<String, String>){
        interactor.addKatarak(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetKatarak(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetKatarak(dataResponse: SuccesResponse) {
        view?.showKatarat(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}