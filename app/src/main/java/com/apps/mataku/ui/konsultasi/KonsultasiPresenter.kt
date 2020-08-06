package com.apps.mataku.ui.konsultasi


interface KonsultasiPresenter {
    fun setViewGetKonsultasi(konsultasiView: KonsultasiView, data:Map<String, String>)
    fun setViewGetDokterKonsultasi(konsultasiView: KonsultasiView, data:Map<String, String>)
    fun setViewUpdateReview(konsultasiView: KonsultasiView, data:Map<String, String>)
    fun setViewUpdateKonsultasi(konsultasiView: KonsultasiView, data:Map<String, String>)
}