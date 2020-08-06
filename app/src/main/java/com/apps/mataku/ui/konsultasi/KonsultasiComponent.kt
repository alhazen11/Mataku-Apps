package com.apps.mataku.ui.konsultasi

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(KonsultasiModule::class)])
interface KonsultasiComponent {
    fun inject(target: KonsultasiActivity)
}