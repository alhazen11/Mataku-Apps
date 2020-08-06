package com.apps.mataku.ui.konsultasi


import com.apps.mataku.api.KonsultasiResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface KonsultasiInteractor {
    fun getKonsultasi(data: Map<String, String>): Observable<KonsultasiResponse>
    fun getDokterKonsultasi(data: Map<String, String>): Observable<KonsultasiResponse>
    fun setReview(data: Map<String, String>): Observable<SuccesResponse>
    fun putKonsultasi(data: Map<String, String>): Observable<SuccesResponse>
}