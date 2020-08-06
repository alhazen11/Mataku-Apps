package com.apps.mataku.ui.chat


import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface ChatInteractor {
    fun addFavorit(data:Map<String, String>):  Observable<SuccesResponse>
    fun getFavorit(data:Map<String, String>):  Observable<FavoritResponse>
}