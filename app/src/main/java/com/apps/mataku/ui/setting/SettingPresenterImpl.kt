package com.apps.mataku.ui.setting

import android.util.Log
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SettingPresenterImpl(val interactor: SettingInteractor, private var view: SettingView?): SettingPresenter{
    override fun setViewSetting(settingView: SettingView, data: Map<String, String>) {
        view = settingView
        getSetting(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getSetting(data: Map<String, String>){
        interactor.putMember(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetSetting(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }

    private fun onGetSetting(dataResponse: SuccesResponse?) {
        view?.showSetting(dataResponse?.status, dataResponse?.error!!, dataResponse?.result!!)
    }
}