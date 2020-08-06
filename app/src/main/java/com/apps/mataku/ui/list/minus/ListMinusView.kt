package com.apps.mataku.ui.list.minus

import com.apps.mataku.models.LogMinus


interface ListMinusView {
    fun showGetListMinus(status: String?, error: Boolean,result: List<LogMinus>?)


}