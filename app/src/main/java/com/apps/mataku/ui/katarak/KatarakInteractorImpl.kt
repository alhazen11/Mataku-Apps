package com.apps.mataku.ui.katarak

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class KatarakInteractorImpl(private val dataDbApi: DataDbApi):KatarakInteractor{
    override fun addKatarak(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addLogKatarak(data)
    }
}