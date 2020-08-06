package com.apps.mataku.ui.list


import com.apps.mataku.api.LogButaWarnaResponse
import com.apps.mataku.api.LogKatarakResponse
import com.apps.mataku.api.LogMinusResponse
import com.apps.mataku.api.LogPterigiumResponse
import rx.Observable

interface ListInteractor {
    fun getButaWarna(data: Map<String, String>): Observable<LogButaWarnaResponse>
    fun getMinus(data: Map<String, String>): Observable<LogMinusResponse>
    fun getKatarak(data: Map<String, String>): Observable<LogKatarakResponse>
    fun getPterigium(data: Map<String, String>): Observable<LogPterigiumResponse>
}