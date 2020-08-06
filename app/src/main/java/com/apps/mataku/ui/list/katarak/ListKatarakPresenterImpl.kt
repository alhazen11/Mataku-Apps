package com.apps.mataku.ui.list.katarak

import android.util.Log
import com.apps.mataku.api.LogKatarakResponse
import com.apps.mataku.ui.list.ListInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListKatarakPresenterImpl(val interactor: ListInteractor, private var view: ListKatarakView?): ListKatarakPresenter{
    override fun setViewListKatarak(listKatarakView: ListKatarakView, data: Map<String, String>) {
        view = listKatarakView
        getListKatarak(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }
    private fun getListKatarak(data: Map<String, String>){
        interactor.getKatarak(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { LogKatarakResponse -> onGetListKatarak(LogKatarakResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetListKatarak(dataResponse: LogKatarakResponse?) {
        view?.showGetListKatarak(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}