package com.apps.mataku.ui.login

import android.util.Log
import com.apps.mataku.api.LoginResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenterImpl(val interactor: LoginInteractor, private var view: LoginView?): LoginPresenter{
    override fun setViewFirebase(loginView: LoginView, data: Map<String, String>) {
        view = loginView
        getFirebase(data)
    }

    override fun setViewLogin(loginView: LoginView, data: Map<String, String>) {
        view = loginView
        getLogin(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }
    private fun getFirebase(id:Map<String, String>){
        interactor.putLogin(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onGetFirebase(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getLogin(id:Map<String, String>){
        interactor.getLogin(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { LoginResponse -> onGetlogin(LoginResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun onGetlogin(dataResponse: LoginResponse) {
        view?.showLogin(dataResponse?.status, dataResponse?.error!!,dataResponse?.result)
    }
    private fun onGetFirebase(dataResponse: SuccesResponse) {
        view?.showFirebase(dataResponse?.status, dataResponse?.error!!,dataResponse?.result)
    }
}