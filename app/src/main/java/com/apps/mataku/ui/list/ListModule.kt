package com.apps.mataku.ui.list

import com.apps.mataku.api.DataDbApi
import com.apps.mataku.di.AppScope
import com.apps.mataku.ui.list.butawarna.ListButaWarnaPresenter
import com.apps.mataku.ui.list.butawarna.ListButaWarnaPresenterImpl
import com.apps.mataku.ui.list.butawarna.ListPterigiumPresenter
import com.apps.mataku.ui.list.katarak.ListKatarakPresenter
import com.apps.mataku.ui.list.katarak.ListKatarakPresenterImpl
import com.apps.mataku.ui.list.minus.ListMinusPresenter
import com.apps.mataku.ui.list.minus.ListMinusPresenterImpl
import com.apps.mataku.ui.list.pterigium.ListPterigiumPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ListModule {
    @Provides
    fun provideListButaWarnaPresenter(inboundInputInteractor: ListInteractor): ListButaWarnaPresenter {
        return ListButaWarnaPresenterImpl(inboundInputInteractor, null)
    }

    @Provides
    @AppScope
    fun provideListInteractor(api : DataDbApi): ListInteractor{
        return ListInteractorImpl(api)
    }
    @Provides
    fun provideListKatarakPresenter(logKatarakInteractor: ListInteractor): ListKatarakPresenter {
        return ListKatarakPresenterImpl(logKatarakInteractor, null)
    }
    @Provides
    fun provideListPterigiumPresenter(logPterigiumInteractor: ListInteractor): ListPterigiumPresenter {
        return ListPterigiumPresenterImpl(logPterigiumInteractor, null)
    }
    @Provides
    fun provideListMinusPresenter(logMinusInteractor: ListInteractor): ListMinusPresenter {
        return ListMinusPresenterImpl(logMinusInteractor, null)
    }

}