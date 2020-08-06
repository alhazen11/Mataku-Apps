package com.apps.mataku.ui.list.pterigium

import android.util.Log
import com.apps.mataku.api.LogPterigiumResponse
import com.apps.mataku.ui.list.ListInteractor
import com.apps.mataku.ui.list.butawarna.ListPterigiumPresenter
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListPterigiumPresenterImpl(val interactor: ListInteractor, private var view: ListPterigiumView?):
    ListPterigiumPresenter {
    override fun setViewListPterigium(listPterigiumView: ListPterigiumView, data: Map<String, String>) {
        view = listPterigiumView
        getListPterigium(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getListPterigium(data: Map<String, String>){
        interactor.getPterigium(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { LogPterigiumResponse -> onGetListPterigium(LogPterigiumResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetListPterigium(dataResponse: LogPterigiumResponse) {
        view?.showGetListPterigium(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }

}