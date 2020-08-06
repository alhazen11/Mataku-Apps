package com.apps.mataku.ui.notifikasi

import okhttp3.RequestBody

interface NotifikasiPresenter {
    fun setViewGetNotifikasi(notifikasiView: NotifikasiView, data:Map<String, String>)
}