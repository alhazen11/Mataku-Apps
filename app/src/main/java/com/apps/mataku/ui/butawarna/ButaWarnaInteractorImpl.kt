package com.apps.mataku.ui.butawarna

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class ButaWarnaInteractorImpl(private val dataDbApi: DataDbApi):ButaWarnaInteractor{
    override fun addButaWarna(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addLogButaWarna(data)
    }
}