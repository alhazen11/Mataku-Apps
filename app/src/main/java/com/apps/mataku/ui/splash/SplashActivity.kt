package com.apps.mataku.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.apps.mataku.R
import com.apps.mataku.ui.login.LoginActivity
import com.apps.mataku.ui.notifikasi.NotifikasiActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handleIntent(getIntent());
    }
    private fun handleIntent(intent: Intent) {
        val go_to_notif = intent.getStringExtra("go_to_notif")
        Log.d("gotonotif",""+go_to_notif)

        if(go_to_notif!==null){
            startActivity(Intent(this@SplashActivity, NotifikasiActivity::class.java))
        }
        else{
            Handler().postDelayed(Runnable { startActivity(Intent(this@SplashActivity, LoginActivity::class.java)) }, 2000)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        handleIntent(getIntent());
    }
}
