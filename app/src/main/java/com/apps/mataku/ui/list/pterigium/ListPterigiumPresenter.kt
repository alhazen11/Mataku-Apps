package com.apps.mataku.ui.list.butawarna

import com.apps.mataku.ui.list.pterigium.ListPterigiumView
import okhttp3.RequestBody

interface ListPterigiumPresenter {
    fun setViewListPterigium(listPterigiumView: ListPterigiumView, data:Map<String, String>)
}