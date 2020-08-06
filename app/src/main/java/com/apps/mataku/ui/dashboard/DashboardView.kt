package com.apps.mataku.ui.dashboard

import com.apps.mataku.models.Member


interface DashboardView {
    fun showMengajukanKonsultasi(status: String?, error: Boolean,result: String?)
    fun showFirebase(status: String?, error: Boolean,result: String?)
    fun showDokter(status: String?, error: Boolean,result: List<Member>?)
}