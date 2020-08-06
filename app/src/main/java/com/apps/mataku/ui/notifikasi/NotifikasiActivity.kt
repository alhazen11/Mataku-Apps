package com.apps.mataku.ui.notifikasi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Notifikasi
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.hmik_up.smarthima.ui.adapter.NotifikasiAdapter
import kotlinx.android.synthetic.main.activity_notifikasi.*

import javax.inject.Inject

class NotifikasiActivity : AppCompatActivity(),NotifikasiView {
    @Inject
    lateinit var presenter: NotifikasiPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""

    override fun showGetNotifikasi(status: String?, error: Boolean, result: List<Notifikasi>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as NotifikasiAdapter).addNotifikasi(result)
        }
    }

    fun kosong(){
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)
        (applicationContext as Apps).createNotifikasiComponent().inject(this)
        kosong()
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        ui_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = NotifikasiAdapter(mContext)
        presenter.setViewGetNotifikasi(mContext, createQueryNotifikasi(idUser))
        ui_back.setOnClickListener { startActivity(Intent(this@NotifikasiActivity, DashboardActivity::class.java)) }
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)
            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                presenter.setViewGetNotifikasi(mContext, createQueryNotifikasi(idUser))
            }, 1000)
        })
    }
    private fun createQueryNotifikasi(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }
}