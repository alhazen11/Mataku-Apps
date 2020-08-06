package com.apps.mataku.ui.konsultasi

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.*
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Konsultasi
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hmik_up.smarthima.ui.adapter.KonsultasiAdapter
import kotlinx.android.synthetic.main.activity_konsultasi.*
import javax.inject.Inject

class KonsultasiActivity : AppCompatActivity(),KonsultasiView,KonsultasiClickInterface {
    override fun showUpdateKonsultasi(status: String?, error: Boolean, result: String?) {
        if(error==false) {
            Toast.makeText(applicationContext, "Konsultasi diterima", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Konsultasi gagal", Toast.LENGTH_SHORT).show()
        }
    }

    @Inject
    lateinit var presenter: KonsultasiPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""
    private var requestOptions = RequestOptions()
    private var listner: KonsultasiClickInterface=this

    override fun showGetDokterKonsultasi(status: String?, error: Boolean, result: List<Konsultasi>?) {
        Log.d("test", error.toString())

        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as KonsultasiAdapter).addKonsultasi(result,listner,isDokter)
        }
    }

    override fun showGetKonsultasi(status: String?, error: Boolean, result: List<Konsultasi>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as KonsultasiAdapter).addKonsultasi(result,listner,isDokter)
        }
    }

    override fun showUpdateReview(status: String?, error: Boolean, result: String?) {
        if(error==false) {
            Toast.makeText(applicationContext, "Review berhasil dikirim", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Review gagal dikirim", Toast.LENGTH_SHORT).show()
        }
    }

    override fun recyclerViewReview(id: String, nama: String, foto: String) {
        dialogReview(id,nama,foto)
    }
    fun kosong(){
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsultasi)
        (applicationContext as Apps).createKonsultasiComponent().inject(this)
        kosong()
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        ui_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = KonsultasiAdapter(mContext)
        getList()
        ui_back.setOnClickListener { startActivity(Intent(this@KonsultasiActivity, DashboardActivity::class.java)) }
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)
            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                getList()
            }, 1000)
        })
    }
    private fun getList(){
        if(isDokter!="1"){
            presenter.setViewGetKonsultasi(mContext, createQueryKonsultasi(idUser))
        }else{
            presenter.setViewGetDokterKonsultasi(mContext, createQueryKonsultasi(idUser))
        }
    }
    private fun createQueryKonsultasi(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }
    private fun createQueryReview(id_user:String,id_user_dokter:String,rate:String,keterangan:String): Map<String, String>{
        return hashMapOf(
            "id_user" to id_user,
            "id_user_dokter" to id_user_dokter,
            "rate" to rate,
            "keterangan" to keterangan
        )
    }
    private fun createQueryUpdateKonsultasi(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user,
            "aktif" to "1"
        )
    }
    fun dialogReview(id:String,nama:String,foto:String){
        if(isDokter!="1") {
            var b: AlertDialog? =null
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getLayoutInflater();
            val dialogView = inflater.inflate(R.layout.dialog_review, null);
            dialogBuilder.setView(dialogView)

            val buttonMengajukan = dialogView.findViewById(R.id.ui_review) as Button
            val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
            val fotoView = dialogView.findViewById(R.id.ui_foto) as ImageView
            val namaView = dialogView.findViewById(R.id.ui_nama) as TextView
            val keteranganView = dialogView.findViewById(R.id.ui_keterangan) as TextView
            val ui_start1 = dialogView.findViewById(R.id.ui_start1) as ImageView
            val ui_start2 = dialogView.findViewById(R.id.ui_start2) as ImageView
            val ui_start3 = dialogView.findViewById(R.id.ui_start3) as ImageView
            val ui_start4 = dialogView.findViewById(R.id.ui_start4) as ImageView
            val ui_start5 = dialogView.findViewById(R.id.ui_start5) as ImageView

            var rate = 0
            namaView.text= nama.toString()
            ui_start1.setOnClickListener {
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start1)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start2)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start3)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start4)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start5)
                rate=1
            }
            ui_start2.setOnClickListener {
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start1)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start2)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start3)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start4)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start5)

                rate=2
            }
            ui_start3.setOnClickListener {
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start1)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start2)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start3)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start4)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start5)

                rate=3
            }
            ui_start4.setOnClickListener {
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start1)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start2)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start3)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start4)
                Glide.with(mContext).load(R.drawable.ic_star_b).into(ui_start5)

                rate=4
            }
            ui_start5.setOnClickListener {
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start1)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start2)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start3)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start4)
                Glide.with(mContext).load(R.drawable.ic_star).into(ui_start5)
                rate=5
            }
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
            Glide.with(this).load(foto).apply(requestOptions).into(fotoView)

            buttonMengajukan.setOnClickListener { presenter.setViewUpdateReview(mContext,createQueryReview(idUser,id,
                rate.toString(),keteranganView.text.toString()))
                b?.dismiss()
            }
            buttonCancel.setOnClickListener { b?.dismiss() }
            b = dialogBuilder.create()
            b.show()
        }else{
            var b: AlertDialog? =null
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getLayoutInflater();
            val dialogView = inflater.inflate(R.layout.dialog_konsultasi_t, null);
            dialogBuilder.setView(dialogView)

            val buttonMengajukan = dialogView.findViewById(R.id.ui_mengajukan) as Button
            val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
            val fotoView = dialogView.findViewById(R.id.ui_foto) as ImageView
            val namaView = dialogView.findViewById(R.id.ui_nama) as TextView
            namaView.text= nama.toString()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
            Glide.with(this).load(foto).apply(requestOptions).into(fotoView)

            buttonMengajukan.setOnClickListener {
                presenter.setViewUpdateKonsultasi(mContext,createQueryUpdateKonsultasi(id.toString()))
                getList()
                b!!.dismiss()
            }
            buttonCancel.setOnClickListener { b?.dismiss() }
            b = dialogBuilder.create()
            b.show()
        }
    }
}
