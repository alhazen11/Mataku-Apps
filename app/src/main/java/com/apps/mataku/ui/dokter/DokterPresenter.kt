package com.apps.mataku.ui.dokter

interface DokterPresenter {
    fun setViewMengajukanKonsultasi(dokterView: DokterView, data: Map<String, String>)
    fun setViewDokter(dokterView: DokterView, data: Map<String, String>)
}