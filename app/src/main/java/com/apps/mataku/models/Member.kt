package com.apps.mataku.models

import com.apps.mataku.BuildConfig

data class Member(
    val id: Int,
    val nama: String,
    val email: String,
    val photo: String,
    val dokter: Int,
    val key_firebase: String,
    val date_created: String
)  {
    fun getFotoUrl(): String
    {
        return BuildConfig.BASE_URL +photo
    }
}