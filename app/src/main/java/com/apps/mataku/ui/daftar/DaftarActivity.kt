package com.apps.mataku.ui.daftar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_daftar.*
import javax.inject.Inject

class DaftarActivity : AppCompatActivity(),DaftarView {
    override fun showDaftar(status: String?, error: Boolean, result: String?) {
        if(error==false) {
            Toast.makeText(
                this,
                "Pendaftaran berhasil.", Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@DaftarActivity, LoginActivity::class.java))
        }else{
            Toast.makeText(
                this,
                "Pendaftaran gagal.", Toast.LENGTH_LONG
            ).show()
        }
    }
    @Inject
    lateinit var presenter: DaftarPresenter
    var mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        (applicationContext as Apps).createDaftarComponent().inject(this)
        ui_daftar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var password =ui_password.text.toString()
                if(ui_password.text.toString()==ui_cpassword.text.toString()){
                    presenter.setViewDaftar(mContext,createQueryDaftar(ui_name.text.toString(),ui_email.text.toString(),password))
                }else{
                    Toast.makeText(
                        mContext,
                        "Password dan konfrimasi password tidak sama.", Toast.LENGTH_LONG
                    ).show()
                }
            }
        });
        ui_masuk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(this@DaftarActivity, LoginActivity::class.java))
            }
        });


    }
    private fun createQueryDaftar(nama:String,email:String,password:String): Map<String, String>{
        return hashMapOf(
            "nama" to nama,
            "email" to email,
            "password" to password
        )
    }
    override fun onBackPressed() {
        startActivity(Intent(this@DaftarActivity, LoginActivity::class.java))
    }
}
