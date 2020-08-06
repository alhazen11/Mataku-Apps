package com.apps.mataku.ui.chat

import com.apps.mataku.models.Favorit

interface ChatView {
    fun showAddFavorit(status: String?, error: Boolean,result: String?)
    fun showGetFavorit(status: String?, error: Boolean,result: List<Favorit>?)
}