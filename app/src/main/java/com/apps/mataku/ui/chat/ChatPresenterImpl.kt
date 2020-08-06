package com.apps.mataku.ui.chat

import android.util.Log
import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ChatPresenterImpl(val interactor: ChatInteractor, private var view: ChatView?): ChatPresenter{
    override fun setViewGetFavorit(chatView: ChatView, data: Map<String, String>) {
        view = chatView
        getFavorit(data)
    }

    override fun setViewAddFavorit(chatView: ChatView, data: Map<String, String>) {
        view = chatView
        addFavorit(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getFavorit(data: Map<String, String>){
        interactor.getFavorit(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { FavoritResponse -> onGetFavorit((FavoritResponse))},
                { e -> onGetFailure(e) }
            )
    }
    private fun addFavorit(data: Map<String, String>){
        interactor.addFavorit(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onAddFavorit((SuccesResponse))},
                { e -> onGetFailure(e) }
            )
    }

    private fun onAddFavorit(dataResponse: SuccesResponse) {
        view?.showAddFavorit(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }

    private fun onGetFavorit(dataResponse: FavoritResponse) {
        view?.showGetFavorit(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}