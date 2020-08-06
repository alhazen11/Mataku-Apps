package com.apps.mataku.ui.butawarna

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(ButaWarnaModule::class)])
interface ButaWarnaComponent {
    fun inject(target: ButaWarnaActivity)
}