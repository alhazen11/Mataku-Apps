package com.apps.mataku.ui.setting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Base64
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.CameraUtils
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_setting.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject

class SettingActivity : AppCompatActivity(),SettingView {
    override fun showSetting(status: String?, error: Boolean, result: String) {
        if(error==false) {
            Toast.makeText(applicationContext, "Profil sudah update", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Profil gagal update", Toast.LENGTH_SHORT).show()
        }
    }
    @Inject
    lateinit var presenter: SettingPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private var lengthbmp: Int = 0
    // key to store image path in savedInstance state
    val KEY_IMAGE_STORAGE_PATH = "image_path"
    private var lastBitmap: Bitmap? = null
    private var image=""
    private val PICK_IMAGE_REQUEST = 1
    val BITMAP_SAMPLE_SIZE = 8
    var tanda:Boolean=false
    val IMAGE_EXTENSION = "jpg"
    private var imageStoragePath: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        (applicationContext as Apps).createSettingComponent().inject(this)
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        var  foto= user.get("foto").toString()
        Glide.with(this).load(foto).into(ui_foto)
        ui_simpan.setOnClickListener {
            var password=ui_password.text.toString()
            if(tanda){
                 image = getStringImage(lastBitmap!!)
                presenter.setViewSetting(mContext,createQueryPhoto(idUser,image))
                tanda=false
            }

            val nama=ui_nama.text.toString()
            if(nama!=""){
                presenter.setViewSetting(mContext,createQueryNama(idUser,nama))
            }
            if(password!=""){
                if(ui_password.text.toString()==ui_cpassword.text.toString()){
                    presenter.setViewSetting(mContext,createQueryPassword(idUser,password))
                }else{
                    Toast.makeText(
                            mContext,
                            "Password dan konfirmasi password tidak sama.", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        ui_back.setOnClickListener { startActivity(Intent(this@SettingActivity, DashboardActivity::class.java)) }
        ui_foto.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageIntent.type = "image/*"
            pickImageIntent.putExtra("aspectX", 1)
            pickImageIntent.putExtra("aspectY", 1)
            pickImageIntent.putExtra("scale", true)
            pickImageIntent.putExtra("outputFormat",
                    Bitmap.CompressFormat.JPEG.toString())
            startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST)
        }
        if (!CameraUtils.isDeviceSupportCamera(applicationContext)) {
            Toast.makeText(applicationContext,
                    "Maaf! Perangkatmu tidak mendukung kamera",
                    Toast.LENGTH_LONG).show()
            // will close the app if the device doesn't have camera
            finish()
        }
        restoreFromBundle(savedInstanceState)
    }
    private fun createQueryNama(id_user:String,nama:String): Map<String, String>{
        return hashMapOf(
                "id" to id_user,
                "nama" to nama
        )
    }
    private fun createQueryPhoto(id_user:String,photo:String): Map<String, String>{
        return hashMapOf(
                "id" to id_user,
                "photo" to photo
        )
    }
    private fun createQueryPassword(id_user:String,password:String): Map<String, String>{
        return hashMapOf(
                "id" to id_user,
                "password" to password
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                tanda=true
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val imageInByte = stream.toByteArray()
                lengthbmp = imageInByte.size / (1024 * 1024)
                lastBitmap = bitmap
                //encoding image to string
                Glide.with(this).load(lastBitmap).into(ui_foto)
                //passing the image to volley
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // get the file url
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH)
    }
    private fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH)) {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH)
                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath!!.substring(imageStoragePath!!.lastIndexOf(".")) == ".$IMAGE_EXTENSION") {
                        previewCapturedImage()
                    }
                }
            }
        }
    }
    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        val encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return encodedImage
    }
    private fun previewCapturedImage() {
        try {
            val bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val imageInByte = stream.toByteArray()
            lengthbmp = imageInByte.size / (1024 * 1024)
            if (lengthbmp >= 7) {
                Toast.makeText(this@SettingActivity, "Max Ukuran Photo 6MB", Toast.LENGTH_SHORT).show()
            }
            lastBitmap = bitmap
            Glide.with(this).load(lastBitmap).into(ui_foto)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
}
