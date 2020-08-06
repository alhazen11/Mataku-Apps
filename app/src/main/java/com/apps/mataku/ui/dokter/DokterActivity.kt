package com.apps.mataku.ui.dokter

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Member
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hmik_up.smarthima.ui.adapter.DokterAdapter
import kotlinx.android.synthetic.main.activity_dokter.*
import java.util.HashMap
import javax.inject.Inject

class DokterActivity : AppCompatActivity(),DokterClickInterface,DokterView {
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
    fun kosong(){
        ui_list_chat.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }

    override fun showDokter(status: String?, error: Boolean, result: List<Member>?) {
        if (result!!.size != 0) {
            ui_list_chat.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list_chat.adapter as DokterAdapter).addMember(result,listner)
        }
    }

    @Inject
    lateinit var presenter: DokterPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""
    private var requestOptions = RequestOptions()
    private var kosong:Map<String, String> = hashMapOf()
    private var listner: DokterClickInterface =this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dokter)
        (applicationContext as Apps).createDokterComponent().inject(this)
        kosong()
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        ui_list_chat.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        ui_list_chat.layoutManager = layoutManager
        ui_list_chat.setHasFixedSize(true)
        ui_list_chat.adapter = DokterAdapter(mContext)
        presenter.setViewDokter(mContext, kosong)
        ui_back.setOnClickListener { startActivity(Intent(this@DokterActivity, DashboardActivity::class.java)) }
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)
            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                presenter.setViewDokter(mContext, kosong)
            }, 1000)
        })
    }
    fun dialogMengajukan(id:Int,nama:String,foto:String){
        if(isDokter!="1") {
            var b: AlertDialog? =null
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getLayoutInflater();
            val dialogView = inflater.inflate(R.layout.dialog_konsultasi, null);
            dialogBuilder.setView(dialogView)

            val buttonMengajukan = dialogView.findViewById(R.id.ui_mengajukan) as Button
            val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
            val fotoView = dialogView.findViewById(R.id.ui_foto) as ImageView
            val namaView = dialogView.findViewById(R.id.ui_nama) as TextView
            namaView.text= nama.toString()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
            Glide.with(this).load(foto).apply(requestOptions).into(fotoView)

            buttonMengajukan.setOnClickListener {
                presenter.setViewMengajukanKonsultasi(mContext,createQueryKonsultasi(idUser,id.toString()))
                b?.dismiss()
            }
            buttonCancel.setOnClickListener { b?.dismiss() }
            b = dialogBuilder.create()
            b.show()
        }else{
            Toast.makeText(applicationContext, "Tidak mempunyai akses ini.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createQueryKonsultasi(id_user:String,id_user_dokter:String): Map<String, String>{
        return hashMapOf(
            "id_user" to id_user,
            "id_user_dokter" to id_user_dokter,
            "aktif" to "2"
        )
    }
}
