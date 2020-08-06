package com.apps.mataku.ui.notifikasi

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class NotifikasiModule {
    @Provides
    fun provideNotifikasiPresenter(notifikasiInteractor: NotifikasiInteractor): NotifikasiPresenter {
        return NotifikasiPresenterImpl(notifikasiInteractor, null)
    }

    @Provides
    @AppScope
    fun provideNotifikasiInteractor(api : DataDbApi): NotifikasiInteractor{
        return NotifikasiInteractorImpl(api)
    }

}