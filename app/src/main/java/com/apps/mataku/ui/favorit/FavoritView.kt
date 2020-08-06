package com.apps.mataku.ui.favorit

import com.apps.mataku.models.Favorit

interface FavoritView {
    fun showFavorit(status: String?, error: Boolean,result: List<Favorit>?)
    fun showFavoritDokter(status: String?, error: Boolean,result: List<Favorit>?)
    fun showDeleteFavorit(status: String?, error: Boolean,result: String)

}