package com.apps.mataku.ui.dashboard

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(DashboardModule::class)])
interface DashboardComponent {
    fun inject(target: DashboardActivity)
}