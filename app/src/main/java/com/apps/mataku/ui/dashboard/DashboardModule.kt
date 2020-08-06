package com.apps.mataku.ui.dashboard

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {
    @Provides
    fun provideDashboardPresenter(inboundInputInteractor: DashboardInteractor): DashboardPresenter {
        return DashboardPresenterImpl(inboundInputInteractor, null)
    }

    @Provides
    @AppScope
    fun provideDashboardInteractor(api : DataDbApi): DashboardInteractor{
        return DashboardInteractorImpl(api)
    }
}