package com.apps.mataku.ui.pterigium

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.util.Base64
import android.view.View
import android.widget.Toast
import com.apps.mataku.Apps
import com.apps.mataku.R
import com.apps.mataku.api.DeepApi
import com.apps.mataku.api.DeepResponse
import com.apps.mataku.ui.dashboard.DashboardActivity
import com.apps.mataku.utils.CameraUtils
import com.apps.mataku.utils.SQLiteHandler
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_pterigium.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback

class PterigiumActivity : AppCompatActivity(),PterigiumView {

    override fun showPterigium(status: String?, error: Boolean, result: String) {}
    @Inject
    lateinit var presenter: PterigiumPresenter
    private var mContext = this
    private var db: SQLiteHandler? = null
    private lateinit var user: HashMap<String, String>
    private var idUser:String=""
    private val CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100
    private var lengthbmp: Int = 0
    // key to store image path in savedInstance state
    val KEY_IMAGE_STORAGE_PATH = "image_path"
    val MEDIA_TYPE_IMAGE = 1
    private var lastBitmap: Bitmap? = null

    private val PICK_IMAGE_REQUEST = 1
    val BITMAP_SAMPLE_SIZE = 8

    val IMAGE_EXTENSION = "jpg"

    private var imageStoragePath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pterigium)
        (applicationContext as Apps).createPterigiumComponent().inject(this)
        db = SQLiteHandler(applicationContext)
        user= db!!.userDetails
        idUser= user.get("uid").toString()
        ui_back.setOnClickListener { startActivity(Intent(this@PterigiumActivity, DashboardActivity::class.java)) }

        if (!CameraUtils.isDeviceSupportCamera(applicationContext)) {
            Toast.makeText(applicationContext,
                    "Maaf! Perangkatmu tidak mendukung kamera",
                    Toast.LENGTH_LONG).show()
            // will close the app if the device doesn't have camera
            finish()
        }
        ui_kamera.setOnClickListener{
            if (CameraUtils.checkPermissions(applicationContext)) {
                captureImage()
            } else {
                requestCameraPermission(MEDIA_TYPE_IMAGE)
            }
        }
        restoreFromBundle(savedInstanceState)

        ui_galeri.setOnClickListener{
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
    }
    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos)
        val imageBytes = baos.toByteArray()
        val encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return "data:image/jpeg;base64,$encodedImage"

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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                ui_gone_g.visibility= View.GONE
                ui_gone_t.visibility=View.GONE
                ui_foto.visibility=View.VISIBLE
                ui_hasil.setText("Loading...")
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
                val imageInByte = stream.toByteArray()
                lengthbmp = imageInByte.size / (1024 * 1024)
                lastBitmap = bitmap
                //encoding image to string
                Glide.with(this).load(lastBitmap).into(ui_foto)
                uploadImage()
                //passing the image to volley
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(applicationContext, imageStoragePath)

                // successfully captured the image
                // display it in image view
                ui_hasil.setText("Loading...")

                previewCapturedImage()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(applicationContext,
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show()
            } else {
                // failed to capture image
                Toast.makeText(applicationContext,
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    /**
     * Requesting permissions using Dexter library
     */
    private fun requestCameraPermission(type: Int) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(

                        object : MultiplePermissionsListener {

                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                captureImage()
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            //showPermissionsAlert()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?, token: PermissionToken?) {
                        token!!.continuePermissionRequest()
                    }
                }).check()
    }


    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE)
        if (file != null) {
            imageStoragePath = file!!.getAbsolutePath()
        }

        val fileUri = CameraUtils.getOutputMediaFileUri(applicationContext, file)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
    }

    /**
     * Saving stored image path to saved instance state
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath)
    }

    /**
     * Restoring image path from saved instance state
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // get the file url
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH)
    }

    private fun previewCapturedImage() {
        try {
            // hide video preview
            ui_gone_g.visibility=View.GONE
            ui_gone_t.visibility=View.GONE
            ui_foto.visibility=View.VISIBLE

            val bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
            val imageInByte = stream.toByteArray()
            lengthbmp = imageInByte.size / (1024 * 1024)
            if (lengthbmp >= 7) {
                Toast.makeText(this@PterigiumActivity, "Max Ukuran Photo 6MB", Toast.LENGTH_SHORT).show()
            }
            lastBitmap = bitmap
            Glide.with(this).load(lastBitmap).into(ui_foto)
            uploadImage()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
    private fun uploadImage(){
        val image = getStringImage(lastBitmap!!).replace("\n", "")
        val apiService = DeepApi.create()
        val call = apiService.uploadPterigium(image)
        call.enqueue(object : Callback<DeepResponse> {
            override fun onFailure(call: Call<DeepResponse>, t: Throwable) {
            }

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<DeepResponse>, response: retrofit2.Response<DeepResponse>?) {
                if (response != null) {
                    ui_acc.visibility=View.VISIBLE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        progressBar.setProgress((response.body()?.acc!!*100).toInt(),true)
                    }else{
                        progressBar.progress= (response.body()?.acc!!*100).toInt()
                    }
                    ui_text_acc.text=String.format(getResources().getString(R.string.accuracy), (response.body()?.acc!!*100).toInt())
                    var hasil = response.body()?.result
                    if (hasil == 0) {
                        ui_hasil.setText("Tidak terdeteksi pterigium")
                        presenter.setViewPterigium(mContext,createQuery(idUser,"Tidak terdeteksi pterigium"))
                    } else {
                        ui_hasil.setText("Terdeteksi pterigium")
                        presenter.setViewPterigium(mContext,createQuery(idUser,"Terdeteksi pterigium"))
                    }
                }
            }
        })

    }
    private fun createQuery(id_user:String,hasil:String): Map<String, String>{
        return hashMapOf(
                "id_user" to id_user,
                "hasil" to hasil
        )
    }
    private fun showPermissionsAlert() {
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        CameraUtils.openSettings(this@PterigiumActivity)
                    }
                })
                .setNegativeButton("CANCEL", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {

                    }
                }).show()
    }
}
