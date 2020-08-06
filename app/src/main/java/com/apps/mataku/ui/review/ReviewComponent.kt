package com.apps.mataku.ui.review

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(ReviewModule::class)])
interface ReviewComponent {
    fun inject(target: ReviewActivity)
}