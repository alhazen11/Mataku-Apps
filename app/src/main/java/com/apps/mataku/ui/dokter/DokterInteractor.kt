package com.apps.mataku.ui.dokter


import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface DokterInteractor {
    fun putKonsultasi(data:Map<String, String>):  Observable<SuccesResponse>
    fun getDokter(data:Map<String, String>):  Observable<MemberResponse>
}