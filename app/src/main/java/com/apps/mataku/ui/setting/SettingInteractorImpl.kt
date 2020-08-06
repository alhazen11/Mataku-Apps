package com.apps.mataku.ui.setting

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.api.SuccesResponse
import rx.Observable

class SettingInteractorImpl(private val dataDbApi: DataDbApi):SettingInteractor{
    override fun putMember(data: Map<String, String>): Observable<SuccesResponse> {
        return dataDbApi.updateMember(data)
    }
}