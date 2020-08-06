package com.apps.mataku.ui.daftar


import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface DaftarInteractor {
    fun addMember(data:Map<String, String>): Observable<SuccesResponse>
}