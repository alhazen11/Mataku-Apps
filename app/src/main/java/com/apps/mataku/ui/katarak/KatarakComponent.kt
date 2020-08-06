package com.apps.mataku.ui.katarak

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(KatarakModule::class)])
interface KatarakComponent {
    fun inject(target: KatarakActivity)
}