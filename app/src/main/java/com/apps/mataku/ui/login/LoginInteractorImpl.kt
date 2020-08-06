package com.apps.mataku.ui.login

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.LoginResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class LoginInteractorImpl(private val dataDbApi: DataDbApi):LoginInteractor{
    override fun putLogin(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.updateMember(data)
    }

    override fun getLogin(data:Map<String, String>): Observable<LoginResponse> {
        return dataDbApi.getLogins(data)
    }
}