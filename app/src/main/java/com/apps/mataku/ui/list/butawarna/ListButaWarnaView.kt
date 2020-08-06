package com.apps.mataku.ui.list.butawarna

import com.apps.mataku.models.LogButaWarna


interface ListButaWarnaView {
    fun showGetListButaWarna(status: String?, error: Boolean,result: List<LogButaWarna>?)
}