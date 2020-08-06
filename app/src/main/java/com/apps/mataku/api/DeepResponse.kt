package com.apps.mataku.api

import com.apps.mataku.models.Favorit
import com.google.gson.annotations.SerializedName

/**
 * Created by root on 11/15/17.
 */
data class DeepResponse (
    @SerializedName("acc")
    var acc: Float,
    @SerializedName("result")
    var result: Int

)