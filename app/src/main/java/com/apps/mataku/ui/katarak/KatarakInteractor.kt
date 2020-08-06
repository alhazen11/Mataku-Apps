package com.apps.mataku.ui.katarak


import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface KatarakInteractor {
    fun addKatarak(data:Map<String, String>):  Observable<SuccesResponse>
}