package com.apps.mataku.ui.favorit

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
import com.apps.mataku.models.Favorit
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hmik_up.smarthima.ui.adapter.FavoritAdapter
import kotlinx.android.synthetic.main.activity_favorit.*
import javax.inject.Inject

class FavoritActivity : AppCompatActivity(),FavoritView,FavoritClickInterface {
    override fun showDeleteFavorit(status: String?, error: Boolean, result: String) {
        if(error==false) {
            Toast.makeText(applicationContext, "Berhasi hapus favorit", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Gagal hapus", Toast.LENGTH_SHORT).show()
        }
        getList()
    }

    override fun recyclerViewHapus(id: String, nama: String, foto: String) {
        dialogFavorit(id,nama,foto)
    }
    fun kosong(){
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }
    override fun showFavorit(status: String?, error: Boolean, result: List<Favorit>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as FavoritAdapter).addFavorit(result,listner)
        }
    }

    override fun showFavoritDokter(status: String?, error: Boolean, result: List<Favorit>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as FavoritAdapter).addFavorit(result,listner)
        }
    }

    @Inject
    lateinit var presenter: FavoritPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""
    private var requestOptions = RequestOptions()
    private var listner: FavoritClickInterface=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
        (applicationContext as Apps).createFavoritComponent().inject(this)
        kosong()
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        ui_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = FavoritAdapter(mContext)
        getList()
        ui_back.setOnClickListener { startActivity(Intent(this@FavoritActivity, DashboardActivity::class.java)) }
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)
            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                getList()
            }, 1000)
        })
    }
    private fun createQueryFavorit(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }

    private fun getList(){
        if(isDokter!="1"){
            presenter.setViewGetFavorit(mContext, createQueryFavorit(idUser))
        }else{
            presenter.setViewGetFavoritDokter(mContext, createQueryFavorit(idUser))
        }
    }
    fun dialogFavorit(id:String,nama:String,foto:String){
        if(isDokter!="1") {
            var b: AlertDialog? =null
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getLayoutInflater();
            val dialogView = inflater.inflate(R.layout.dialog_favorit, null);
            dialogBuilder.setView(dialogView)

            val buttonMengajukan = dialogView.findViewById(R.id.ui_mengajukan) as Button
            val buttonCancel = dialogView.findViewById(R.id.ui_cancel) as Button
            val fotoView = dialogView.findViewById(R.id.ui_foto) as ImageView
            val namaView = dialogView.findViewById(R.id.ui_nama) as TextView
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(24))
            Glide.with(this).load(foto).apply(requestOptions).into(fotoView)

            buttonMengajukan.setOnClickListener {
                presenter.setViewDeleteFavorit(mContext,createQueryFavorit(idUser))
                b!!.dismiss()
            }
            buttonCancel.setOnClickListener { b?.dismiss() }
            b = dialogBuilder.create()
            b.show()
        }else{

        }
    }
}
