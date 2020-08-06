package com.apps.mataku.ui.favorit


import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface FavoritInteractor {
    fun getFavorit(data:Map<String, String>): Observable<FavoritResponse>
    fun getDokterFavorit(data:Map<String, String>): Observable<FavoritResponse>
    fun deleteFavorit(data:Map<String, String>): Observable<SuccesResponse>

}