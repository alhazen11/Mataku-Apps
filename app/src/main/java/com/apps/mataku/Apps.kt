package com.apps.mataku

import android.app.Activity
import android.app.Application
import android.text.SpannableString
import android.util.Log
import android.view.View
import android.widget.Toast
import com.apps.mataku.di.AppComponent
import com.apps.mataku.di.AppModule
import com.apps.mataku.di.DaggerAppComponent
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
import com.apps.mataku.utils.Constants
import com.apps.mataku.utils.Utils
import com.apps.mataku.utils.Utils.readSharedSetting
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.google.firebase.FirebaseApp

class Apps : Application(){
    lateinit var appComponent : AppComponent
    // lateinit var movieComponent: MovieComponent
    lateinit var loginComponent: LoginComponent
    lateinit var daftarComponent: DaftarComponent
    lateinit var dashboardComponent: DashboardComponent
    lateinit var dokterComponent: DokterComponent
    lateinit var konsultasiComponent: KonsultasiComponent
    lateinit var reviewComponent: ReviewComponent
    lateinit var favoritComponent: FavoritComponent
    lateinit var notifikasiComponent: NotifikasiComponent
    lateinit var katarakComponent: KatarakComponent
    lateinit var pterigiumComponent: PterigiumComponent
    lateinit var butawarnaComponent: ButaWarnaComponent
    lateinit var minusComponent: MinusComponent
    lateinit var listComponent: ListComponent
    lateinit var settingComponent: SettingComponent
    lateinit var chatComponent: ChatComponent


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this);
        appComponent = createAppComponent()
    }
    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this)).build()
    }

    fun createLoginComponent(): LoginComponent {
        loginComponent = appComponent.plus(LoginModule())
        return loginComponent
    }


    fun createDashboardComponent(): DashboardComponent {
        dashboardComponent = appComponent.plus(DashboardModule())
        return dashboardComponent
    }

    fun createDokterComponent(): DokterComponent {
        dokterComponent = appComponent.plus(DokterModule())
        return dokterComponent
    }
    fun createKonsultasiComponent(): KonsultasiComponent {
        konsultasiComponent = appComponent.plus(KonsultasiModule())
        return konsultasiComponent
    }
    fun createReviewComponent(): ReviewComponent {
        reviewComponent = appComponent.plus(ReviewModule())
        return reviewComponent
    }
    fun createFavoritComponent(): FavoritComponent {
        favoritComponent = appComponent.plus(FavoritModule())
        return favoritComponent
    }
    fun createNotifikasiComponent(): NotifikasiComponent {
        notifikasiComponent = appComponent.plus(NotifikasiModule())
        return notifikasiComponent
    }
    fun createKatarakComponent(): KatarakComponent {
        katarakComponent = appComponent.plus(KatarakModule())
        return katarakComponent
    }
    fun createPterigiumComponent(): PterigiumComponent {
        pterigiumComponent = appComponent.plus(PterigiumModule())
        return pterigiumComponent
    }
    fun createButaWarnaComponent(): ButaWarnaComponent {
        butawarnaComponent = appComponent.plus(ButaWarnaModule())
        return butawarnaComponent
    }
    fun createMinusComponent(): MinusComponent {
        minusComponent = appComponent.plus(MinusModule())
        return minusComponent
    }
    fun createListComponent(): ListComponent {
        listComponent = appComponent.plus(ListModule())
        return listComponent
    }
    fun createSettingComponent(): SettingComponent {
        settingComponent = appComponent.plus(SettingModule())
        return settingComponent
    }
    fun createDaftarComponent(): DaftarComponent {
        daftarComponent = appComponent.plus(DaftarModule())
        return daftarComponent
    }
    fun createChatComponent(): ChatComponent {
        chatComponent = appComponent.plus(ChatModule())
        return chatComponent
    }
    /*



    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }
    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(this)).build()
    }

    fun createMainComponent(): MovieComponent {
        movieComponent = appComponent.plus(MovieModule())
        return movieComponent
    }

    fun createDetailComponent(): DetailComponent{
        detailComponent = appComponent.plus(DetailModule())
        return detailComponent
    }
    */

    fun releaseMainComponent(){

    }

    fun welcoming(context : Activity, view: View, title: String, msg: String){
        val isUserFirstTime = java.lang.Boolean.valueOf(readSharedSetting(context, Constants.PREF_USER_FIRST_TIME, "true"))
        if (isUserFirstTime){
            val spannedDesc = SpannableString(msg)
            TapTargetView.showFor(context, TapTarget.forView(view, title, spannedDesc)
                .cancelable(false)
                .drawShadow(true)
                .titleTextDimen(R.dimen.title_text_size)
                .tintTarget(false), object : TapTargetView.Listener() {

                override fun onTargetClick(view: TapTargetView?) {
                    super.onTargetClick(view)
                    Utils.saveSharedSetting(context, Constants.PREF_USER_FIRST_TIME, "false")
                }

                override fun onOuterCircleClick(view: TapTargetView?) {
                    super.onOuterCircleClick(view)
                    Toast.makeText(view!!.context, "You clicked the outer circle!", Toast.LENGTH_SHORT).show()
                }

                override fun onTargetDismissed(view: TapTargetView?, userInitiated: Boolean) {
                    Log.d("TapTargetViewSample", "You dismissed me :(")
                }
            })
        }
    }
}