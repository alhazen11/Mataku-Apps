package com.apps.mataku.ui.chat

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.FavoritResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class ChatInteractorImpl(private val dataDbApi: DataDbApi):ChatInteractor{
    override fun addFavorit(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addFavorit(data)
    }

    override fun getFavorit(data: Map<String, String>): Observable<FavoritResponse> {
        return dataDbApi.getFavorit(data)
    }
}