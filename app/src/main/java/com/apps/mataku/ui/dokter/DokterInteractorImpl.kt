package com.apps.mataku.ui.dokter

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class DokterInteractorImpl(private val dataDbApi: DataDbApi):DokterInteractor{
    override fun getDokter(data: Map<String, String>): Observable<MemberResponse> {
        return dataDbApi.getDokter(data)
    }

    override fun putKonsultasi(data:Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addKonsultasi(data)
    }
}