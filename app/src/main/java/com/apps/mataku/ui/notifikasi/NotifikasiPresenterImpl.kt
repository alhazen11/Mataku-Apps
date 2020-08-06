package com.apps.mataku.ui.notifikasi

import android.util.Log
import com.apps.mataku.api.NotifikasiResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NotifikasiPresenterImpl(val interactor: NotifikasiInteractor, private var view: NotifikasiView?): NotifikasiPresenter{
    override fun setViewGetNotifikasi(notifikasiView: NotifikasiView, data: Map<String, String>) {
        view = notifikasiView
        getNotifikasi(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getNotifikasi(data: Map<String, String>){
        interactor.getNotifikasi(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { NotifikasiResponse -> onGetNotifikasi(NotifikasiResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetNotifikasi(dataResponse: NotifikasiResponse?) {
        view?.showGetNotifikasi(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}