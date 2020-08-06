package com.apps.mataku.ui.dokter

import android.util.Log
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DokterPresenterImpl(val interactor: DokterInteractor, private var view: DokterView?): DokterPresenter{
    override fun setViewDokter(dokterView: DokterView, data: Map<String, String>) {
        view = dokterView
        getDokter(data)
    }

    override fun setViewMengajukanKonsultasi(dokterView: DokterView, data: Map<String, String>) {
        view = dokterView
        getMengajukanKonsultasi(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getMengajukanKonsultasi(data: Map<String, String>){
        interactor.putKonsultasi(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetMengajukanKonsultasi(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getDokter(data: Map<String, String>){
        interactor.getDokter(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { MemberResponse -> onGetDokter(MemberResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetDokter(dataResponse: MemberResponse) {
        view?.showDokter(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }

    private fun onGetMengajukanKonsultasi(dataResponse: SuccesResponse) {
        view?.showMengajukanKonsultasi(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}