package com.apps.mataku.ui.dashboard

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class DashboardInteractorImpl(private val dataDbApi: DataDbApi): DashboardInteractor{
    override fun getDokter(data: Map<String, String>): Observable<MemberResponse> {
        return dataDbApi.getDokter(data)
    }

    override fun getFirebase(data:Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.updateMember(data)
    }

    override fun putKonsultasi(data:Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addKonsultasi(data)
    }
}