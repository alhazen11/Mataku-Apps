package com.apps.mataku.ui.konsultasi

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.KonsultasiResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class KonsultasiInteractorImpl(private val dataDbApi: DataDbApi):KonsultasiInteractor{
    override fun putKonsultasi(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.updateKonsultasi(data)
    }

    override fun getDokterKonsultasi(data: Map<String, String>): Observable<KonsultasiResponse> {
        return dataDbApi.getKonsultasiDokter(data)
    }

    override fun setReview(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.addReview(data)
    }

    override fun getKonsultasi(data: Map<String, String>): Observable<KonsultasiResponse> {
        return dataDbApi.getKonsultasi(data)
    }
}