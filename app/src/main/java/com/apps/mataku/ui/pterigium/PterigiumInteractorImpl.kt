package com.apps.mataku.ui.pterigium

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class PterigiumInteractorImpl(private val dataDbApi: DataDbApi):PterigiumInteractor{
    override fun addPterigium(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addLogPterigium(data)
    }

}