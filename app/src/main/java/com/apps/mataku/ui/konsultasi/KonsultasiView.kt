package com.apps.mataku.ui.konsultasi

import com.apps.mataku.models.Konsultasi

interface KonsultasiView {
    fun showGetKonsultasi(status: String?, error: Boolean,result: List<Konsultasi>?)
    fun showGetDokterKonsultasi(status: String?, error: Boolean,result: List<Konsultasi>?)
    fun showUpdateReview(status: String?, error: Boolean,result: String?)
    fun showUpdateKonsultasi(status: String?, error: Boolean,result: String?)
}