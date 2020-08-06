package com.apps.mataku.ui.daftar

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(DaftarModule::class)])
interface DaftarComponent {
    fun inject(target: DaftarActivity)
}