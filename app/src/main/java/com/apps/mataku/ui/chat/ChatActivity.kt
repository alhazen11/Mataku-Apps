package com.apps.mataku.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.models.Favorit
import com.apps.mataku.models.Message
import com.apps.mataku.ui.konsultasi.KonsultasiActivity
import com.apps.mataku.utils.SCUtils
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.hmik_up.smarthima.ui.adapter.ChatAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(),ChatView {
    override fun showAddFavorit(status: String?, error: Boolean, result: String?) {
        if(error==false) {
            Toast.makeText(applicationContext, "Berhasi menambahkan favorit", Toast.LENGTH_SHORT).show()
            Glide.with(this).load(R.drawable.ic_favorit).into(ui_favorit)
        }else{
            Toast.makeText(applicationContext, "Gagal menambahkan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showGetFavorit(status: String?, error: Boolean, result: List<Favorit>?) {
        for(i in 0..result!!.size){
            if(userDokterID==result[i].id_user_dokter){
                sudahF=true
                Glide.with(this).load(R.drawable.ic_favorit).into(ui_favorit)
            }
        }
    }

    @Inject
    lateinit var presenter: ChatPresenter
    val ANTI_FLOOD_SECONDS = 3 //simple anti-flood
    private val PROFANITY_FILTER_ACTIVE = true
    private var database: FirebaseDatabase? = null
    private var listFavorit: List<Favorit>? = null
    private var sudahF:Boolean = false
    private var userID: String? = null
    private var userNama: String? = null
    private var userFoto: String? = null
    private var username: String? = null
    private var fotoDokter: String? = null
    private var namaDokter: String? = null
    private var userDokterID: String? = null

    private lateinit var mContext: Context
    private var adapter: ChatAdapter? = null
    private var databaseRef: DatabaseReference? = null
    internal var messageArrayList = ArrayList<Message>()
    private var last_message_timestamp: Long = 0
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        (applicationContext as Apps).createChatComponent().inject(this)
        userID = intent.getStringExtra("idUser")
        userDokterID =intent.getStringExtra("idDokter")
        fotoDokter =intent.getStringExtra("fotoDokter")
        namaDokter =intent.getStringExtra("namaDokter")
        mContext = this
        database = FirebaseDatabase.getInstance()
        databaseRef = database!!.getReference()
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        username=user.get("uid").toString()
        userNama= user.get("name").toString()
        userFoto=user.get("foto").toString()
        if(username==userDokterID){
            ui_nama.text=namaDokter
        }else{
            ui_nama.text=namaDokter
        }
        presenter.setViewGetFavorit(this,createQueryFavorit(username.toString()))
        ui_list_chat.setLayoutManager(LinearLayoutManager(this))
        adapter = ChatAdapter(mContext)
        adapter!!.addMessage(messageArrayList, username!!,userFoto!!,userNama!!,userDokterID!!,fotoDokter!!,namaDokter!!)
        ui_list_chat.setAdapter(adapter)
        ui_favorit.setOnClickListener{
            if(!sudahF){
                presenter.setViewAddFavorit(this,createQueryAddFavorit(username!!, userDokterID!!))
            }
        }
        databaseRef!!.child("the_messages").child(userID+""+userDokterID).limitToLast(50).addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val new_message = dataSnapshot.getValue(Message::class.java!!)
                messageArrayList.add(new_message!!)
                adapter!!.addMessage(messageArrayList, username!!,userFoto!!,userNama!!,userDokterID!!,fotoDokter!!,namaDokter!!)
                adapter!!.notifyDataSetChanged()
                ui_list_chat.scrollToPosition(adapter!!.getItemCount() - 1)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                messageArrayList.remove(dataSnapshot.getValue(Message::class.java!!))
                adapter!!.notifyDataSetChanged()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        ui_back.setOnClickListener { startActivity(Intent(this@ChatActivity, KonsultasiActivity::class.java)) }

        ui_send.setOnClickListener {
            process_new_message(ui_chat.text.toString(), false)
        }
    }
    private fun createQueryFavorit(id_user:String): Map<String, String>{
        return hashMapOf(
            "id" to id_user
        )
    }
    private fun createQueryAddFavorit(id_user:String,id_user_dokter:String): Map<String, String>{
        return hashMapOf(
            "id_user" to id_user,
            "id_user_dokter" to id_user_dokter

        )
    }
    private fun process_new_message(new_message: String, isNotification: Boolean) {
        var new_message = new_message
        if (new_message.isEmpty()) {
            return
        }

        //simple anti-flood protection
        if (System.currentTimeMillis() / 1000L - last_message_timestamp < ANTI_FLOOD_SECONDS) {
            SCUtils.showErrorSnackBar(
                Activity(),
                findViewById<android.view.View>(android.R.id.content),
                "You cannot send messages so fast."
            ).show()
            return
        }
        ui_chat.setText("")
        val xmessage =
            Message(username.toString(), username.toString(), new_message, System.currentTimeMillis() / 1000L, isNotification)
        val key = databaseRef!!.child("the_messages").child(userID+""+userDokterID).push().key
        databaseRef!!.child("the_messages").child(userID+""+userDokterID).child(key!!).setValue(xmessage)
        last_message_timestamp = System.currentTimeMillis() / 1000L
    }
}
