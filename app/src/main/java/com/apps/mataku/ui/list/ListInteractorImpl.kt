package com.apps.mataku.ui.list

import com.apps.mataku.api.*
import okhttp3.RequestBody
import rx.Observable

class ListInteractorImpl(private val dataDbApi: DataDbApi):ListInteractor{
    override fun getButaWarna(data: Map<String, String>): Observable<LogButaWarnaResponse> {
        return dataDbApi.getLogButaWarna(data)
    }

    override fun getMinus(data: Map<String, String>): Observable<LogMinusResponse> {
        return dataDbApi.getLogMinus(data)
    }

    override fun getKatarak(data: Map<String, String>): Observable<LogKatarakResponse> {
        return dataDbApi.getLogKatarak(data)
    }

    override fun getPterigium(data: Map<String, String>): Observable<LogPterigiumResponse> {
        return dataDbApi.getLogPterigium(data)
    }
}