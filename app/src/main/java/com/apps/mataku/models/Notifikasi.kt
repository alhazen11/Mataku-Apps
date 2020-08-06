package com.apps.mataku.models

data class Notifikasi(
    val id: Int,
    val id_user: String,
    val activity: String,
    val keterangan: String,
    val date_created: String,
    val user:Info
)  {
}