package com.apps.mataku.ui.login


import com.apps.mataku.api.LoginResponse
import com.apps.mataku.api.MemberResponse
import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface LoginInteractor {
    fun getLogin(data:Map<String, String>): Observable<LoginResponse>
    fun putLogin(data:Map<String, String>): Observable<SuccesResponse>
}
