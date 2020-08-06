package com.apps.mataku.api

import com.apps.mataku.models.LogPterigium
import com.google.gson.annotations.SerializedName

/**
 * Created by root on 11/15/17.
 */
data class LogPterigiumResponse (
    @SerializedName("status")
    var status: String,
    @SerializedName("error")
    var error: Boolean,
    @SerializedName("result")
    var result: List<LogPterigium>
)