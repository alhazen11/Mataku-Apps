package com.apps.mataku.ui.notifikasi



import com.apps.mataku.api.NotifikasiResponse
import rx.Observable

interface NotifikasiInteractor {
    fun getNotifikasi(data: Map<String, String>): Observable<NotifikasiResponse>

}