package com.apps.mataku.ui.login

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(LoginModule::class)])
interface LoginComponent {
    fun inject(target: LoginActivity)
}