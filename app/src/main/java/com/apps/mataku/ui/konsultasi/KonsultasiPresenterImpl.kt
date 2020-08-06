package com.apps.mataku.ui.konsultasi

import android.util.Log
import com.apps.mataku.api.KonsultasiResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class KonsultasiPresenterImpl(val interactor: KonsultasiInteractor, private var view: KonsultasiView?): KonsultasiPresenter{
    override fun setViewUpdateKonsultasi(konsultasiView: KonsultasiView, data: Map<String, String>) {
        view = konsultasiView
        getUpdateKonsultasi(data)
    }

    override fun setViewGetDokterKonsultasi(konsultasiView: KonsultasiView, data: Map<String, String>) {
        view = konsultasiView
        getDokterKonsultasi(data)
    }

    override fun setViewGetKonsultasi(konsultasiView: KonsultasiView, data: Map<String, String>) {
        view = konsultasiView
        getKonsultasi(data)
    }

    override fun setViewUpdateReview(konsultasiView: KonsultasiView, data: Map<String, String>) {
        view = konsultasiView
        getUpdateReview(data)
    }

    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getKonsultasi(data: Map<String, String>){
        interactor.getKonsultasi(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { KonsultasiResponse -> onGetKonsultasi(KonsultasiResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getDokterKonsultasi(data: Map<String, String>){
        interactor.getDokterKonsultasi(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { KonsultasiResponse -> onGetDokterKonsultasi(KonsultasiResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getUpdateReview(data: Map<String, String>){
        interactor.setReview(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetReview(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getUpdateKonsultasi(data: Map<String, String>){
        interactor.putKonsultasi(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onPutKonsultasi(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun onGetDokterKonsultasi(dataResponse: KonsultasiResponse) {
        view?.showGetKonsultasi(dataResponse?.status,dataResponse?.error, dataResponse?.result!!)
    }
    private fun onGetKonsultasi(dataResponse: KonsultasiResponse) {
        view?.showGetDokterKonsultasi(dataResponse?.status,dataResponse?.error, dataResponse?.result!!)
    }
    private fun onGetReview(dataResponse: SuccesResponse?) {
        view?.showUpdateReview(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
    private fun onPutKonsultasi(dataResponse: SuccesResponse?) {
        view?.showUpdateKonsultasi(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}