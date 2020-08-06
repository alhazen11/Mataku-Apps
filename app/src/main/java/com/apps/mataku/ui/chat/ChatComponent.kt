package com.apps.mataku.ui.chat

import com.apps.mataku.di.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(ChatModule::class)])
interface ChatComponent {
    fun inject(target: ChatActivity)
}