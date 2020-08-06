package com.apps.mataku.ui.pterigium

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PterigiumPresenterImpl(val interactor: PterigiumInteractor, private var view: PterigiumView?): PterigiumPresenter{
    override fun setViewPterigium(pterigiumView: PterigiumView, data: Map<String, String>) {
        view = pterigiumView
        getPterigium(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getPterigium(data: Map<String, String>){
        interactor.addPterigium(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetPterigium(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetPterigium(dataResponse: SuccesResponse?) {
        view?.showPterigium(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}