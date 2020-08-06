package com.apps.mataku.ui.chat

interface ChatPresenter {
    fun setViewGetFavorit(chatView: ChatView, data: Map<String, String>)
    fun setViewAddFavorit(chatView: ChatView, data: Map<String, String>)
}