package com.apps.mataku.ui.favorit

import android.util.Log
import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.SuccesResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FavoritPresenterImpl(val interactor: FavoritInteractor, private var view: FavoritView?): FavoritPresenter{
    override fun setViewDeleteFavorit(favoritView: FavoritView, data: Map<String, String>) {
        view = favoritView
        deleteFavorit(data)
    }

    override fun setViewGetFavorit(favoritView: FavoritView, data: Map<String, String>) {
        view = favoritView
        getFavorit(data)
    }

    override fun setViewGetFavoritDokter(favoritView: FavoritView, data: Map<String, String>) {
        view = favoritView
        getFavoritDokter(data)
    }


    private fun onGetFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun getFavoritDokter(data: Map<String, String>){
        interactor.getDokterFavorit(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { FavoritResponse -> onFavoritDokter(FavoritResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun getFavorit(data: Map<String, String>){
        interactor.getFavorit(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { FavoritResponse -> onGetFavorit(FavoritResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun deleteFavorit(data: Map<String, String>){
        interactor.deleteFavorit(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { SuccesResponse -> onDeleteFavorit(SuccesResponse)},
                { e -> onGetFailure(e) }
            )
    }
    private fun onDeleteFavorit(dataResponse: SuccesResponse) {
        view?.showDeleteFavorit(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
    private fun onGetFavorit(dataResponse: FavoritResponse) {
        view?.showFavorit(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
    private fun onFavoritDokter(dataResponse: FavoritResponse?) {
        view?.showFavoritDokter(dataResponse?.status, dataResponse?.error!!,dataResponse?.result!!)
    }
}