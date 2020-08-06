package com.apps.mataku.ui.favorit

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class FavoritInteractorImpl(private val dataDbApi: DataDbApi):FavoritInteractor{
    override fun deleteFavorit(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.deleteFavorit(data)
    }

    override fun getDokterFavorit(data: Map<String, String>): Observable<FavoritResponse> {
        return dataDbApi.getFavoritDokter(data)
    }

    override fun getFavorit(data:Map<String, String>): Observable<FavoritResponse> {
        return dataDbApi.getFavorit(data)
    }
}