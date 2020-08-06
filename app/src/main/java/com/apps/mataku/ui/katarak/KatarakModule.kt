package com.apps.mataku.ui.katarak

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class KatarakModule {
    @Provides
    fun provideKatarakPresenter(katarakInteractor: KatarakInteractor): KatarakPresenter {
        return KatarakPresenterImpl(katarakInteractor, null)
    }

    @Provides
    @AppScope
    fun provideKatarakInteractor(api : DataDbApi): KatarakInteractor{
        return KatarakInteractorImpl(api)
    }

}