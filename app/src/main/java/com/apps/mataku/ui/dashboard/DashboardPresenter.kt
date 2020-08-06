package com.apps.mataku.ui.dashboard

import okhttp3.RequestBody

interface DashboardPresenter {
    fun setViewMengajukanKonsultasi(dashboardView: DashboardView, data: Map<String, String>)
    fun setViewFirebase(dashboardView: DashboardView, data: Map<String, String>)
    fun setViewDokter(dashboardView: DashboardView, data: Map<String, String>)
}