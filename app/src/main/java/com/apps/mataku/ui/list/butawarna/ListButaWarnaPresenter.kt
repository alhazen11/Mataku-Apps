package com.apps.mataku.ui.list.butawarna

import okhttp3.RequestBody

interface ListButaWarnaPresenter {
    fun setViewListButaWarna(listButaWarnaView: ListButaWarnaView, data:Map<String, String>)
}