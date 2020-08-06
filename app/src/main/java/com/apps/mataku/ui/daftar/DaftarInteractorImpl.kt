package com.apps.mataku.ui.daftar

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class DaftarInteractorImpl(private val dataDbApi: DataDbApi):DaftarInteractor{
    override fun addMember(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addMember(data)
    }
}