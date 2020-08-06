package com.apps.mataku.ui.setting


import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface SettingInteractor {
    fun putMember(data:Map<String, String>):  Observable<SuccesResponse>
}