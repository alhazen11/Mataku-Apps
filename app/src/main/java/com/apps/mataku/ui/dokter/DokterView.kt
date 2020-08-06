package com.apps.mataku.ui.dokter

import com.apps.mataku.models.Member

interface DokterView {
    fun showMengajukanKonsultasi(status: String?, error: Boolean,result: String?)
    fun showDokter(status: String?, error: Boolean,result: List<Member>?)
}