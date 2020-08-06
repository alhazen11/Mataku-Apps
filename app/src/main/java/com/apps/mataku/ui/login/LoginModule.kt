package com.apps.mataku.ui.login

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginPresenter(loginInteractor: LoginInteractor): LoginPresenter{
        return LoginPresenterImpl(loginInteractor, null)
    }

    @Provides
    @AppScope
    fun provideLoginInteractor(api : DataDbApi): LoginInteractor{
        return LoginInteractorImpl(api)
    }

}