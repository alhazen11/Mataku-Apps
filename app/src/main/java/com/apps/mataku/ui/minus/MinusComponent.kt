package com.apps.mataku.ui.minus

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(MinusModule::class)])
interface MinusComponent {
    fun inject(target: MinusActivity)
}