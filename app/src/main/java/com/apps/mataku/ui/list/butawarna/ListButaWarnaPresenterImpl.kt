package com.apps.mataku.ui.list.butawarna

import android.util.Log
import com.apps.mataku.api.LogButaWarnaResponse
import com.apps.mataku.ui.list.ListInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListButaWarnaPresenterImpl(val interactor: ListInteractor, private var view: ListButaWarnaView?): ListButaWarnaPresenter{
    override fun setViewListButaWarna(listButaWarnaView: ListButaWarnaView, data: Map<String, String>) {
        view = listButaWarnaView
        getListButaWarna(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }
    private fun getListButaWarna(data: Map<String, String>){
        interactor.getButaWarna(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { LogButaWarnaResponse -> onGetListButaWarna(LogButaWarnaResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetListButaWarna(dataResponse: LogButaWarnaResponse) {
        view?.showGetListButaWarna(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}