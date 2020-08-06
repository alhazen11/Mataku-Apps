package com.apps.mataku.ui.butawarna

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ButaWarnaModule {
    @Provides
    fun provideButaWarnaPresenter(butaWarnaInteractor: ButaWarnaInteractor): ButaWarnaPresenter {
        return ButaWarnaPresenterImpl(butaWarnaInteractor, null)
    }

    @Provides
    @AppScope
    fun provideButaWarnaInteractor(api : DataDbApi): ButaWarnaInteractor{
        return ButaWarnaInteractorImpl(api)
    }

}