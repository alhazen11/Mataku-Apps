package com.apps.mataku.ui.notifikasi

import com.apps.mataku.models.Notifikasi


interface NotifikasiView {
    fun showGetNotifikasi(status: String?, error: Boolean,result: List<Notifikasi>?)
}