package com.apps.mataku.models

import com.apps.mataku.BuildConfig

data class Info(
    val nama: String,
    val photo: String
)  {
    fun getFotoUrl(): String
    {
        return BuildConfig.BASE_URL +photo
    }
}