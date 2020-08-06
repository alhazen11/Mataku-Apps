package com.apps.mataku.ui.dokter

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(DokterModule::class)])
interface DokterComponent {
    fun inject(target: DokterActivity)
}