package com.apps.mataku.ui.list.minus

import android.util.Log
import com.apps.mataku.api.LogMinusResponse
import com.apps.mataku.ui.list.ListInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListMinusPresenterImpl(val interactor: ListInteractor, private var view: ListMinusView?): ListMinusPresenter{
    override fun setViewListMinus(listMinusView: ListMinusView, data: Map<String, String>) {
        view = listMinusView
        getListMinus(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getListMinus(data: Map<String, String>){
        interactor.getMinus(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { LogMinusResponse -> onGetListMinus(LogMinusResponse)},
                { e -> onGetFailure(e) }
            )
    }


    private fun onGetListMinus(dataResponse: LogMinusResponse) {
        view?.showGetListMinus(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}