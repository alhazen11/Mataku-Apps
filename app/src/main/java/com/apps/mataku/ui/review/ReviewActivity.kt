package com.apps.mataku.ui.review

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Review
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.hmik_up.smarthima.ui.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.activity_review.*
import javax.inject.Inject

class ReviewActivity : AppCompatActivity(),ReviewView {
    @Inject
    lateinit var presenter: ReviewPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var isDokter:String=""
    fun kosong(){
        ui_list.visibility= View.GONE
        ui_swipe.visibility= View.GONE
        ui_kosong.visibility= View.VISIBLE
    }
    override fun showReview(status: String?, error: Boolean, result: List<Review>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as ReviewAdapter).addReview(result)
        }
    }

    override fun showReviewDokter(status: String?, error: Boolean, result: List<Review>?) {
        if (result!!.size != 0) {
            ui_list.visibility= View.VISIBLE
            ui_swipe.visibility= View.VISIBLE
            ui_kosong.visibility= View.GONE
            (ui_list.adapter as ReviewAdapter).addReview(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        (applicationContext as Apps).createReviewComponent().inject(this)
        kosong()
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        isDokter = user.get("dokter").toString()
        ui_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        ui_list.layoutManager = layoutManager
        ui_list.setHasFixedSize(true)
        ui_list.adapter = ReviewAdapter(mContext)
        getList()
        ui_back.setOnClickListener { startActivity(Intent(this@ReviewActivity, DashboardActivity::class.java)) }
        ui_swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            ui_swipe.setRefreshing(true)
            Handler().postDelayed(Runnable {
                ui_swipe.setRefreshing(false)
                kosong()
                getList()
            }, 1000)
        })
    }
    private fun createQueryReview(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }

    private fun getList(){
        if(isDokter!="1"){
            presenter.setViewGetReview(mContext, createQueryReview(idUser))
        }else{
            presenter.setViewGetReviewDokter(mContext, createQueryReview(idUser))
        }
    }
}
