package com.apps.mataku.ui.login

import com.apps.mataku.models.Member


interface LoginView {
    fun showLogin(status: String?, error: Boolean,result: Member?)
    fun showFirebase(status: String?, error: Boolean,result: String?)
}