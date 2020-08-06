package com.apps.mataku.ui.pterigium

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(PterigiumModule::class)])
interface PterigiumComponent {
    fun inject(target: PterigiumActivity)
}