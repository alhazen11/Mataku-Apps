package com.apps.mataku.ui.favorit

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class FavoritModule {
    @Provides
    fun provideInboundInputPresenter(inboundInputInteractor: FavoritInteractor): FavoritPresenter {
        return FavoritPresenterImpl(inboundInputInteractor, null)
    }

    @Provides
    @AppScope
    fun provideInboundInputInteractor(api : DataDbApi): FavoritInteractor{
        return FavoritInteractorImpl(api)
    }

}