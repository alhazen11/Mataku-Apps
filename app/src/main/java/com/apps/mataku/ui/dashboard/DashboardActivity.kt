package com.apps.mataku.ui.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Member
import com.apps.mataku.ui.butawarna.ButaWarnaActivity
import com.apps.mataku.ui.dokter.DokterActivity
import com.apps.mataku.ui.favorit.FavoritActivity
import com.apps.mataku.ui.katarak.KatarakActivity
import com.apps.mataku.ui.konsultasi.KonsultasiActivity
import com.apps.mataku.ui.list.ListActivity
import com.apps.mataku.ui.login.LoginActivity
import com.apps.mataku.ui.minus.MinusActivity
import com.apps.mataku.ui.notifikasi.NotifikasiActivity
import com.apps.mataku.ui.pterigium.PterigiumActivity
import com.apps.mataku.ui.review.ReviewActivity
import com.apps.mataku.ui.setting.SettingActivity
import com.apps.mataku.utils.SQLiteHandler
import com.apps.mataku.utils.SessionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.iid.FirebaseInstanceId
import com.hmik_up.smarthima.ui.adapter.DashDokterAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.HashMap
import javax.inject.Inject

class DashboardActivity : AppCompatActivity(),DashboardView,DashClickInterface {
    override fun recyclerViewMengajukanOnClick(id: Int, nama: String, foto: String) {
        dialogMengajukan(id,nama,foto)
    }

    override fun showMengajukanKonsultasi(status: String?, error: Boolean, result: String?) {
        if(error==false) {
            Toast.makeText(applicationContext, "Behasil mengajukan", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Gagal Mengajukan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showFirebase(status: String?, error: Boolean, result: String?) {
    }

    override fun showDokter(status: String?, error: Boolean, result: List<Member>?) {
        if (result!!.size != 0) {
            (ui_list.adapter as DashDokterAdapter).addMember(result,listner)
        }
    }

    @Inject
    lateinit var presenter: DashboardPresenter
    private var mContext = this
    private var session: SessionManager? = null
    private var db: SQLiteHandler? = null
    private var token:String? = ""
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""
    private var requestOptions = RequestOptions()
    private var listner: DashClickInterface=this
    private var kosong:Map<String, String> = hashMapOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        (applicationContext as Apps).createDashboardComponent().inject(this)
        db = SQLiteHandler(applicationContext)
        token = FirebaseInstanceId.getInstance().token
        user= db!!.userDetails
        ui_name.text="Hai "+user.get("name")
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))

        Glide.with(this).load(user?.get("foto")).apply(requestOptions).into(ui_foto)

        ui_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = DashDokterAdapter(mContext)

        session = SessionManager(applicationContext)
        presenter.setViewDokter(mContext, kosong)
        presenter.setViewFirebase(mContext, createQueryFirebase(user.get("uid").toString(), token!!))
        ui_logout.setOnClickListener { dialogLogout() }
        ui_log.setOnClickListener { startActivity(Intent(this@DashboardActivity, ListActivity::class.java)) }
        ui_dokter.setOnClickListener { startActivity(Intent(this@DashboardActivity, DokterActivity::class.java)) }
        ui_buta_warna.setOnClickListener { startActivity(Intent(this@DashboardActivity, ButaWarnaActivity::class.java)) }
        ui_miopi.setOnClickListener { startActivity(Intent(this@DashboardActivity, MinusActivity::class.java)) }
        ui_favorit.setOnClickListener { startActivity(Intent(this@DashboardActivity, FavoritActivity::class.java)) }
        ui_katarak.setOnClickListener { startActivity(Intent(this@DashboardActivity, KatarakActivity::class.java)) }
        ui_konsultasi.setOnClickListener { startActivity(Intent(this@DashboardActivity, KonsultasiActivity::class.java)) }
        ui_notifikasi.setOnClickListener { startActivity(Intent(this@DashboardActivity, NotifikasiActivity::class.java)) }
        ui_pterigium.setOnClickListener { startActivity(Intent(this@DashboardActivity, PterigiumActivity::class.java)) }
        ui_review.setOnClickListener { startActivity(Intent(this@DashboardActivity, ReviewActivity::class.java)) }
        ui_setting.setOnClickListener { startActivity(Intent(this@DashboardActivity, SettingActivity::class.java)) }
        ui_next_dokter.setOnClickListener { startActivity(Intent(this@DashboardActivity, DokterActivity::class.java)) }
    }
    override fun onBackPressed() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }
    private fun logoutUser() {
        session!!.setLogin(false)
        db!!.deleteUsers()
        // Launching the login activity
        val intent = Intent(this@DashboardActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    fun dialogLogout(){
        var b: AlertDialog? =null
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = getLayoutInflater();
        val dialogView = inflater.inflate(R.layout.dialog_logout, null);
        dialogBuilder.setView(dialogView)
        val buttonLogout = dialogView.findViewById(R.id.ui_logout) as Button
        val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
        buttonLogout.setOnClickListener { logoutUser() }
        buttonCancel.setOnClickListener { b?.dismiss() }
        b = dialogBuilder.create()
        b.show()
    }
    fun dialogMengajukan(id:Int,nama:String,foto:String){
        if(isDokter!="1") {
            var b: AlertDialog? = null
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getLayoutInflater();
            val dialogView = inflater.inflate(R.layout.dialog_konsultasi, null);
            dialogBuilder.setView(dialogView)

            val buttonMengajukan = dialogView.findViewById(R.id.ui_mengajukan) as Button
            val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
            val fotoView = dialogView.findViewById(R.id.ui_foto) as ImageView
            val namaView = dialogView.findViewById(R.id.ui_nama) as TextView
            namaView.text = nama.toString()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
            Glide.with(this).load(foto).apply(requestOptions).into(fotoView)

            buttonMengajukan.setOnClickListener {
                presenter.setViewMengajukanKonsultasi(
                    mContext,
                    createQueryKonsultasi(idUser, id.toString())
                )
                b!!.dismiss()
            }
            buttonCancel.setOnClickListener { b!!.dismiss() }
            b = dialogBuilder.create()
            b.show()
        }else{
            Toast.makeText(applicationContext, "Tidak mempunyai akses ini.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun createQueryFirebase(id:String,key:String): Map<String, String>{
        return hashMapOf(
            "id" to id,
            "key_firebase" to key
        )
    }
    private fun createQueryKonsultasi(id_user:String,id_user_dokter:String): Map<String, String>{
        return hashMapOf(
            "id_user" to id_user,
            "id_user_dokter" to id_user_dokter,
            "aktif" to "2"
        )
    }
}
