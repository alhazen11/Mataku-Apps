package com.apps.mataku.ui.setting

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(SettingModule::class)])
interface SettingComponent {
    fun inject(target: SettingActivity)
}