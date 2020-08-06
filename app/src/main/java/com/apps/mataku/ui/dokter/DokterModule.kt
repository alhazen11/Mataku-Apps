package com.apps.mataku.ui.dokter

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class DokterModule {
    @Provides
    fun provideDokterPresenter(dokterInteractor: DokterInteractor): DokterPresenter {
        return DokterPresenterImpl(dokterInteractor, null)
    }

    @Provides
    @AppScope
    fun provideInboundInputInteractor(api : DataDbApi): DokterInteractor {
        return DokterInteractorImpl(api)
    }
}