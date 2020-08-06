package com.apps.mataku.ui.list.pterigium

import com.apps.mataku.models.LogPterigium


interface ListPterigiumView {
    fun showGetListPterigium(status: String?, error: Boolean,result: List<LogPterigium>?)

}