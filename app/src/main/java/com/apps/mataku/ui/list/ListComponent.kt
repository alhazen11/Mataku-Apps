package com.apps.mataku.ui.list

import com.apps.mataku.di.AppScope
import com.apps.mataku.ui.list.butawarna.ListButaWarnaFregment
import com.apps.mataku.ui.list.katarak.ListKatarakFregment
import com.apps.mataku.ui.list.minus.ListMinusFregment
import com.apps.mataku.ui.list.pterigium.ListPterigiumFregment
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [(ListModule::class)])
interface ListComponent {
    fun inject(target: ListButaWarnaFregment)
    fun inject(target: ListMinusFregment)
    fun inject(target: ListPterigiumFregment)
    fun inject(target: ListKatarakFregment)
}