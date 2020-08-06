package com.apps.mataku.ui.butawarna

import okhttp3.RequestBody

interface ButaWarnaPresenter {
    fun setViewButaWarna(butaWarnaView: ButaWarnaView, data: Map<String, String>)
}