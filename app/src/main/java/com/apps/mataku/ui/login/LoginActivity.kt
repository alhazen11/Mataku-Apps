package com.apps.mataku.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Member
import com.apps.mataku.ui.daftar.DaftarActivity
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.apps.mataku.utils.SessionManager
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(),LoginView {
    override fun showFirebase(status: String?, error: Boolean, result: String?) {
    }

    @Inject
    lateinit var presenter: LoginPresenter
    private var session: SessionManager? = null
    private var db: SQLiteHandler? = null
    private var email: String = ""
    private var password: String = ""
    private var mContext = this
    private var token:String? = ""
    override fun showLogin(status: String?, error: Boolean, result: Member?) {
        if(error==false){
            session!!.setLogin(true)
            var id = result!!.id
            var name = result.nama
            var email = result.email
            var foto = result.getFotoUrl()
            var dokter = result.dokter
            var key = result.key_firebase
            presenter.setViewFirebase(mContext, createQueryFirebase(id.toString(), token!!))
            // Inserting row in users table
            db?.addUser(id, name, email, foto, dokter, key)
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }else{
            Toast.makeText(applicationContext, "Email atau Password anda salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (applicationContext as Apps).createLoginComponent().inject(this)
        db = SQLiteHandler(applicationContext)
        token = FirebaseInstanceId.getInstance().token

        session = SessionManager(applicationContext)

        if (session!!.isLoggedIn) {
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }
        ui_login.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                email= ui_email.text.toString()
                password= ui_password.text.toString()
                if (!email.isEmpty() && !password.isEmpty()) {
                    presenter.setViewLogin(mContext, createQueryLogin(email,password))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Masukan username dan password", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
        ui_daftar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@LoginActivity, DaftarActivity::class.java))
            }
        });
    }
    override fun onBackPressed() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }
    private fun createQueryLogin(email:String,password:String): Map<String, String>{
        return hashMapOf(
            "email" to email,
            "password" to password
        )
    }
    private fun createQueryFirebase(id:String,key:String): Map<String, String>{
        return hashMapOf(
            "id" to id,
            "key_firebase" to key
        )
    }
}
