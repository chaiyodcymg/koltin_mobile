package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.pac.kotlin_mobile.databinding.ActivityHomeBinding



import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
class HomeActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null
    lateinit var binding : ActivityHomeBinding
    lateinit var AUTH : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImageSelect.setOnClickListener {
            openImageChooser()
        }

        binding.btnImageUpload.setOnClickListener {
            uploadImage()
        }

    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE  )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    binding.imageSelected.setImageURI(selectedImageUri)
                }
            }
        }
    }


    private fun uploadImage() {


        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)


        val body = UploadRequestBody(file, "image")
    var api : UserAPI =    Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)

        api.uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {

            }
        })

    }



    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
    override fun onResume() {
        super.onResume()
        Log.i("Event", "Login ")

    }
    override fun onBackPressed() {
        finishAffinity()
    }
    fun logout(v: View){
        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        AUTH.edit{clear()}
        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)
    }

}


