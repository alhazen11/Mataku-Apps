package com.apps.mataku.ui.daftar

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class DaftarModule {
    @Provides
    fun provideDaftarPresenter(daftarInteractor: DaftarInteractor): DaftarPresenter {
        return DaftarPresenterImpl(daftarInteractor, null)
    }

    @Provides
    @AppScope
    fun provideDaftarInteractor(api : DataDbApi): DaftarInteractor{
        return DaftarInteractorImpl(api)
    }

}