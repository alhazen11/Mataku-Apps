package com.apps.mataku.ui.pterigium


import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface PterigiumInteractor {
    fun addPterigium(data:Map<String, String>):  Observable<SuccesResponse>
}