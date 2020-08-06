package com.apps.mataku.ui.minus

import okhttp3.RequestBody

interface MinusPresenter {
    fun setViewMinus(minusView: MinusView, data: Map<String, String>)
}