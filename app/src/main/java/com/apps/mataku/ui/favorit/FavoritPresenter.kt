package com.apps.mataku.ui.favorit


interface FavoritPresenter {
    fun setViewGetFavorit(favoritView: FavoritView,  data:Map<String, String>)
    fun setViewDeleteFavorit(favoritView: FavoritView,  data:Map<String, String>)
    fun setViewGetFavoritDokter(
        favoritView: FavoritView,
        data:Map<String, String>    )
}