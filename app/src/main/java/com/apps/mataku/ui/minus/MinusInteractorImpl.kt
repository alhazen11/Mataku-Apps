package com.apps.mataku.ui.minus

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class MinusInteractorImpl(private val dataDbApi: DataDbApi):MinusInteractor{
    override fun addMinus(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addLogMinus(data)
    }
}