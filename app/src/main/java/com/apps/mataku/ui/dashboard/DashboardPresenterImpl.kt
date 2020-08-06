package com.apps.mataku.ui.dashboard

import android.util.Log
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DashboardPresenterImpl(val interactor: DashboardInteractor, private var view: DashboardView?): DashboardPresenter{
    override fun setViewDokter(dashboardView: DashboardView, data: Map<String, String>) {
        view = dashboardView
        getDokter(data)
    }

    override fun setViewMengajukanKonsultasi(dashboardView: DashboardView, data: Map<String, String>) {
        view = dashboardView
        getMengajukanKonsultasi(data)
    }

    override fun setViewFirebase(dashboardView: DashboardView, data: Map<String, String>) {
        view = dashboardView
        getFirebase(data)
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
    private fun getFirebase(data: Map<String, String>){
        interactor.getFirebase(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetFirebase(SuccesResponse)},
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
    private fun onGetFirebase(dataResponse: SuccesResponse?) {
        view?.showFirebase(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}