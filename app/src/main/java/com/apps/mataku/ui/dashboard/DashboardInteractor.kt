package com.apps.mataku.ui.dashboard


import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface DashboardInteractor {
    fun getFirebase(data:Map<String, String>): Observable<SuccesResponse>
    fun putKonsultasi(data:Map<String, String>):  Observable<SuccesResponse>
    fun getDokter(data:Map<String, String>):  Observable<MemberResponse>

}