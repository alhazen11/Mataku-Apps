package com.apps.mataku.ui.setting

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class SettingModule {
    @Provides
    fun provideSettingPresenter(settingInteractor: SettingInteractor): SettingPresenter {
        return SettingPresenterImpl(settingInteractor, null)
    }

    @Provides
    @AppScope
    fun provideSettingInteractor(api : DataDbApi): SettingInteractor{
        return SettingInteractorImpl(api)
    }
}