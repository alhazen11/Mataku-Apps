package com.apps.mataku.ui.chat

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ChatModule {
    @Provides
    fun provideChatPresenter(chatInteractor: ChatInteractor): ChatPresenter {
        return ChatPresenterImpl(chatInteractor, null)
    }

    @Provides
    @AppScope
    fun provideInboundInputInteractor(api : DataDbApi): ChatInteractor{
        return ChatInteractorImpl(api)
    }

}