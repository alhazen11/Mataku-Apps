package com.apps.mataku.ui.list.katarak

import com.apps.mataku.models.LogKatarak

interface ListKatarakView {
    fun showGetListKatarak(status: String?, error: Boolean,result: List<LogKatarak>?)

}