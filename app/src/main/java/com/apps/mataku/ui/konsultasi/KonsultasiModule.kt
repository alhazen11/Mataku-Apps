package com.apps.mataku.ui.konsultasi

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class KonsultasiModule {
    @Provides
    fun provideInboundInputPresenter(inboundInputInteractor: KonsultasiInteractor): KonsultasiPresenter {
        return KonsultasiPresenterImpl(inboundInputInteractor, null)
    }

    @Provides
    @AppScope
    fun provideInboundInputInteractor(api : DataDbApi): KonsultasiInteractor{
        return KonsultasiInteractorImpl(api)
    }

}