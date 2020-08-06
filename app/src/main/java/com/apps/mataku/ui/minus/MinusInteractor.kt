package com.apps.mataku.ui.minus

import com.apps.mataku.api.SuccesResponse
import rx.Observable

interface MinusInteractor {
    fun addMinus(data:Map<String, String>):  Observable<SuccesResponse>
}