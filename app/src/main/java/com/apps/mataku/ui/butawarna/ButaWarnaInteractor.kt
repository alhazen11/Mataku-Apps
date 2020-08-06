package com.apps.mataku.ui.butawarna


import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface ButaWarnaInteractor {
    fun addButaWarna(data:Map<String, String>):  Observable<SuccesResponse>
}