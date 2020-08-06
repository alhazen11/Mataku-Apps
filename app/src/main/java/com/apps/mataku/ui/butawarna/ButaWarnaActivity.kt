package com.apps.mataku.ui.butawarna

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.ui.daftar.DaftarPresenter
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_buta_warna.*
import javax.inject.Inject

class ButaWarnaActivity : AppCompatActivity(),ButaWarnaView {
    override fun showButaWarna(status: String?, error: Boolean, result: String) {
    }
    @Inject
    lateinit var presenter: ButaWarnaPresenter
    var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    var answer = arrayOf(12,15,35, 5, 57, 18, 73, 74, 8, 96)
    var pilihan1 = arrayOf(12,13,11,14)
    var pilihan2 = arrayOf(13,15,14,16)
    var pilihan3 = arrayOf(33,34,35,36)
    var pilihan4 = arrayOf(5,6,7,4)
    var pilihan5 = arrayOf(55,57,56,54)
    var pilihan6 = arrayOf(16,17,15,18)
    var pilihan7 = arrayOf(72,73,71,74)
    var pilihan8 = arrayOf(74,75,76,77)
    var pilihan9 = arrayOf(6,7,9,8)
    var pilihan10 = arrayOf(95,96,97,99)
    var page=0
    var hasil=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buta_warna)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        (applicationContext as Apps).createButaWarnaComponent().inject(this)
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        ui_a_1.text= pilihan1[0].toString()
        ui_a_2.text= pilihan1[1].toString()
        ui_a_3.text= pilihan1[2].toString()
        ui_a_4.text= pilihan1[3].toString()
        Glide.with(mContext).load(R.drawable.gmb12).into(ui_object)
        ui_back.setOnClickListener { startActivity(Intent(this@ButaWarnaActivity, DashboardActivity::class.java)) }

        ui_a_1.setOnClickListener{
            if(ui_a_1.text==answer[page].toString()){
                hasil+=1
            }
            change()
            final()
        }
        ui_a_2.setOnClickListener{
            if(ui_a_2.text==answer[page].toString()){
                hasil+=1
            }
            change()
            final()
        }
        ui_a_3.setOnClickListener{
            if(ui_a_3.text==answer[page].toString()){
                hasil+=1
            }
            change()
            final()
        }
        ui_a_4.setOnClickListener{
            if(ui_a_4.text==answer[page].toString()){
                hasil+=1
            }
            change()
            final()
        }
        ui_tidak.setOnClickListener{
            change()
            final()
        }
        ui_kembali.setOnClickListener{
            ui_a_1.text= pilihan1[0].toString()
            ui_a_2.text= pilihan1[1].toString()
            ui_a_3.text= pilihan1[2].toString()
            ui_a_4.text= pilihan1[3].toString()
            Glide.with(mContext).load(R.drawable.gmb12).into(ui_object)
            page=0
            hasil=0
            ui_number.text=""+(page+1)+"/10"
            ui_lay_obj.visibility= View.VISIBLE
            ui_lay_hasil.visibility= View.GONE
        }
    }
    fun final(){
        if(page==10){
            ui_lay_obj.visibility= View.GONE
            ui_lay_hasil.visibility= View.VISIBLE
            ui_hasil.text=resources.getString(R.string.hasil_butawarna)+" "+hasil+" "+resources.getString(R.string.of_test)
            presenter.setViewButaWarna(mContext,createQuery(idUser,ui_hasil.text.toString()))
        }
    }
    fun change(){
        ui_number.text=""+(page+2)+"/10"
        if(page==0){
            ui_a_1.text= pilihan2[0].toString()
            ui_a_2.text= pilihan2[1].toString()
            ui_a_3.text= pilihan2[2].toString()
            ui_a_4.text= pilihan2[3].toString()
            Glide.with(mContext).load(R.drawable.gmb15).into(ui_object)
        }else if(page==1){
            ui_a_1.text= pilihan3[0].toString()
            ui_a_2.text= pilihan3[1].toString()
            ui_a_3.text= pilihan3[2].toString()
            ui_a_4.text= pilihan3[3].toString()
            Glide.with(mContext).load(R.drawable.gmb35).into(ui_object)
        }else if(page==2){
            ui_a_1.text= pilihan4[0].toString()
            ui_a_2.text= pilihan4[1].toString()
            ui_a_3.text= pilihan4[2].toString()
            ui_a_4.text= pilihan4[3].toString()
            Glide.with(mContext).load(R.drawable.gmb5).into(ui_object)
        }else if(page==3){
            ui_a_1.text= pilihan5[0].toString()
            ui_a_2.text= pilihan5[1].toString()
            ui_a_3.text= pilihan5[2].toString()
            ui_a_4.text= pilihan5[3].toString()
            Glide.with(mContext).load(R.drawable.gmb57).into(ui_object)
        }else if(page==4){
            ui_a_1.text= pilihan6[0].toString()
            ui_a_2.text= pilihan6[1].toString()
            ui_a_3.text= pilihan6[2].toString()
            ui_a_4.text= pilihan6[3].toString()
            Glide.with(mContext).load(R.drawable.gmb6).into(ui_object)
        }else if(page==5){
            ui_a_1.text= pilihan7[0].toString()
            ui_a_2.text= pilihan7[1].toString()
            ui_a_3.text= pilihan7[2].toString()
            ui_a_4.text= pilihan7[3].toString()
            Glide.with(mContext).load(R.drawable.gmb73).into(ui_object)
        }else if(page==6){
            ui_a_1.text= pilihan8[0].toString()
            ui_a_2.text= pilihan8[1].toString()
            ui_a_3.text= pilihan8[2].toString()
            ui_a_4.text= pilihan8[3].toString()
            Glide.with(mContext).load(R.drawable.gmb74).into(ui_object)
        }else if(page==7){
            ui_a_1.text= pilihan9[0].toString()
            ui_a_2.text= pilihan9[1].toString()
            ui_a_3.text= pilihan9[2].toString()
            ui_a_4.text= pilihan9[3].toString()
            Glide.with(mContext).load(R.drawable.gmb8).into(ui_object)
        }else if(page==8){
            ui_a_1.text= pilihan10[0].toString()
            ui_a_2.text= pilihan10[1].toString()
            ui_a_3.text= pilihan10[2].toString()
            ui_a_4.text= pilihan10[3].toString()
            Glide.with(mContext).load(R.drawable.gmb96).into(ui_object)
        }
        page+=1
    }
    private fun createQuery(id_user:String,hasil:String): Map<String, String>{
        return hashMapOf(
                "id_user" to id_user,
                "hasil" to hasil
        )
    }
}
