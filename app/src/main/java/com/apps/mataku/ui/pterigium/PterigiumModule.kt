package com.apps.mataku.ui.pterigium

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class PterigiumModule {
    @Provides
    fun providePterigiumPresenter(pterigiumInteractor: PterigiumInteractor): PterigiumPresenter {
        return PterigiumPresenterImpl(pterigiumInteractor, null)
    }

    @Provides
    @AppScope
    fun providePterigiumInteractor(api : DataDbApi): PterigiumInteractor{
        return PterigiumInteractorImpl(api)
    }

}