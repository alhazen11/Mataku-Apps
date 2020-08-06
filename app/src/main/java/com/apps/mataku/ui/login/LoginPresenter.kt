package com.apps.mataku.ui.login


interface LoginPresenter {
    fun setViewLogin(loginView: LoginView, data:Map<String, String>)
    fun setViewFirebase(loginView: LoginView, data:Map<String, String>)

}