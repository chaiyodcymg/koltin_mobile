package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityEditProfileBinding
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

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditProfileBinding
    var URL_API = URL.URL_API
    var Select_Page : Int = R.id.page_1
    var image_url = ""
    var email_user = "aa"
    var fname = ""
    var lname = ""
    var selectedImageUri : Uri? = null
    var REQUEST_CODE_PICK_IMAGE = 100
    lateinit var AUTH : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        var data = intent.extras
        image_url = data?.getString("image_url").toString()
        email_user = data?.getString("email_user").toString()
        fname = data?.getString("fname").toString()
        lname = data?.getString("lname").toString()
        binding.imageSelected.setImageURI(null)
        Glide.with(applicationContext).load(image_url).into(binding.imageSelected)
        binding.email.setText(email_user)
        binding.firstname.setText(fname)
        binding.lastname.setText(lname)



        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        supportActionBar!!.elevation = 0.0F
        val view = supportActionBar!!.customView
        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        imageButton.setOnClickListener {
            val intent = intent.putExtra("Select_Page",Select_Page)
            setResult( Activity.RESULT_OK,intent)
            finish()
        }
        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }
        binding.btnSave.setOnClickListener {
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
                    binding.imageSelected.setImageURI(null)
                    binding.imageSelected.setImageURI(selectedImageUri)
                }
            }
        }
    }


    private fun uploadImage() {

        val email = binding.email.text.toString()
        val fname = binding.firstname.text.toString()
        val lname = binding.lastname.text.toString()

        var api : UserAPI =   Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)

        if (selectedImageUri != null){


            val parcelFileDescriptor =  contentResolver?.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            var name = ""
            val returnCursor =contentResolver?.query(selectedImageUri!!, null, null, null, null)
            if (returnCursor != null) {
                val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                returnCursor.moveToFirst()
                name = returnCursor.getString(nameIndex)
                returnCursor.close()
            }
            val file = File(cacheDir,  name)

            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)

            val body = UploadRequestBody(file, "image")

            api.updateprofile_withImage(
                MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    body
                ),
                RequestBody.create(MediaType.parse("multipart/form-data"),AUTH.getString("id","")) ,
                RequestBody.create(MediaType.parse("multipart/form-data"), email ),
                RequestBody.create(MediaType.parse("multipart/form-data"), fname),
                RequestBody.create(MediaType.parse("multipart/form-data"), lname)

            ).enqueue(object : Callback<UploadResponse> {

                override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"แก้ไขสำเร็จ ", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {

                }


            })
        }else{

            api.updateprofile_noImage(AUTH.getString("id","")!! , email , fname, lname)
                .enqueue(object : Callback<Update> {

                    override fun onResponse(call: Call<Update>, response: Response<Update>) {
                        if (response.isSuccessful()){
                            Toast.makeText(applicationContext,"แก้ไขสำเร็จ ", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<Update>, t: Throwable) {

                    }
                })
        }
    }
}