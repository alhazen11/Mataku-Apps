package com.apps.mataku.ui.notifikasi

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.NotifikasiResponse
import rx.Observable

class NotifikasiInteractorImpl(private val dataDbApi: DataDbApi):NotifikasiInteractor{
    override fun getNotifikasi(data: Map<String, String>): Observable<NotifikasiResponse> {
        return dataDbApi.getNotifikasi(data)
    }
}