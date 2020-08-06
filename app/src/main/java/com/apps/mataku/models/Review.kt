package com.apps.mataku.models


data class Review(
    val id: Int,
    val id_user: String,
    val id_user_dokter: String,
    val rate: String,
    val keterangan: String,
    val date_created: String,
    val user:Info
)  {
}