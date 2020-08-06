package com.apps.mataku.ui.daftar

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import okhttp3.RequestBody
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DaftarPresenterImpl(val interactor: DaftarInteractor, private var view: DaftarView?): DaftarPresenter{
    override fun setViewDaftar(daftarView: DaftarView, data: Map<String, String>) {
        view = daftarView
        getDaftar(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getDaftar(data:Map<String, String>){
        interactor.addMember(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetDaftar(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun onGetDaftar(dataResponse: SuccesResponse?) {
        view?.showDaftar(dataResponse?.status, dataResponse?.error!!,dataResponse?.result)
    }
}