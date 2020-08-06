package com.apps.mataku.models

data class Favorit(
    val id: Int,
    val id_user: String,
    val id_user_dokter: String,
    val date_created: String,
    val user:Info
)  {
}