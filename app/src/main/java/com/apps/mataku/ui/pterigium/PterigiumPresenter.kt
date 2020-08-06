package com.apps.mataku.ui.pterigium

import okhttp3.RequestBody

interface PterigiumPresenter {
    fun setViewPterigium(pterigiumView: PterigiumView, data: Map<String, String>)

}