package com.apps.mataku.ui.notifikasi

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(NotifikasiModule::class)])
interface NotifikasiComponent {
    fun inject(target: NotifikasiActivity)
}