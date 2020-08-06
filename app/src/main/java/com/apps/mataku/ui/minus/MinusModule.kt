package com.apps.mataku.ui.minus

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class MinusModule {
    @Provides
    fun provideMinusPresenter(minusInteractor: MinusInteractor): MinusPresenter {
        return MinusPresenterImpl(minusInteractor, null)
    }

    @Provides
    @AppScope
    fun provideMinusInteractor(api : DataDbApi): MinusInteractor{
        return MinusInteractorImpl(api)
    }

}