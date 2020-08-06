package com.apps.mataku.ui.list.minus

import okhttp3.RequestBody

interface ListMinusPresenter {
    fun setViewListMinus(listMinusView: ListMinusView, data:Map<String, String>)
}