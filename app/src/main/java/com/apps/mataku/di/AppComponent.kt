package com.apps.mataku.di

import ccom.apps.mataku.api.ApiModule
import com.apps.mataku.network.NetworkModule
import com.apps.mataku.ui.butawarna.ButaWarnaComponent
import com.apps.mataku.ui.butawarna.ButaWarnaModule
import com.apps.mataku.ui.chat.ChatComponent
import com.apps.mataku.ui.chat.ChatModule
import com.apps.mataku.ui.daftar.DaftarComponent
import com.apps.mataku.ui.daftar.DaftarModule
import com.apps.mataku.ui.dashboard.DashboardComponent
import com.apps.mataku.ui.dashboard.DashboardModule
import com.apps.mataku.ui.dokter.DokterComponent
import com.apps.mataku.ui.dokter.DokterModule
import com.apps.mataku.ui.favorit.FavoritComponent
import com.apps.mataku.ui.favorit.FavoritModule
import com.apps.mataku.ui.katarak.KatarakComponent
import com.apps.mataku.ui.katarak.KatarakModule
import com.apps.mataku.ui.konsultasi.KonsultasiComponent
import com.apps.mataku.ui.konsultasi.KonsultasiModule
import com.apps.mataku.ui.list.ListComponent
import com.apps.mataku.ui.list.ListModule
import com.apps.mataku.ui.login.LoginComponent
import com.apps.mataku.ui.login.LoginModule
import com.apps.mataku.ui.minus.MinusComponent
import com.apps.mataku.ui.minus.MinusModule
import com.apps.mataku.ui.notifikasi.NotifikasiComponent
import com.apps.mataku.ui.notifikasi.NotifikasiModule
import com.apps.mataku.ui.pterigium.PterigiumComponent
import com.apps.mataku.ui.pterigium.PterigiumModule
import com.apps.mataku.ui.review.ReviewComponent
import com.apps.mataku.ui.review.ReviewModule
import com.apps.mataku.ui.setting.SettingComponent
import com.apps.mataku.ui.setting.SettingModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by root on 11/15/17.
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (ApiModule::class)])

interface AppComponent{
    fun plus(loginModule: LoginModule): LoginComponent
    fun plus(dokterModule: DokterModule): DokterComponent
    fun plus(knsultasiModule: KonsultasiModule): KonsultasiComponent
    fun plus(reviewModule: ReviewModule): ReviewComponent
    fun plus(favoritModule: FavoritModule): FavoritComponent
    fun plus(notifikasiModule: NotifikasiModule): NotifikasiComponent
    fun plus(katarakModule: KatarakModule): KatarakComponent
    fun plus(pterigiumModule: PterigiumModule): PterigiumComponent
    fun plus(butawarnaModule: ButaWarnaModule): ButaWarnaComponent
    fun plus(minusModule: MinusModule): MinusComponent
    fun plus(listModule: ListModule): ListComponent
    fun plus(settingModule: SettingModule): SettingComponent
    fun plus(daftarModule: DaftarModule): DaftarComponent
    fun plus(dashboardModule: DashboardModule): DashboardComponent
    fun plus(chatModule: ChatModule): ChatComponent

    //fun plus(movieModule: MovieModule): MovieComponent
    //fun plus(baseModule: BaseModule): BaseComponent
    //fun plus(loginModule: LoginModule): LoginComponent
    //fun plus(detailPengajuanModule: DetailPengajuanModule): DetailPengajuanComponent
}