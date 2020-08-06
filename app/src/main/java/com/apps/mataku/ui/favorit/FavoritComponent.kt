package com.apps.mataku.ui.favorit

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(FavoritModule::class)])
interface FavoritComponent {
    fun inject(target: FavoritActivity)
}