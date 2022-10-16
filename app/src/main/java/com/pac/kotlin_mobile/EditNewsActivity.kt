package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityEditNewsBinding
import com.pac.kotlin_mobile.databinding.ActivityEditProfileBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class EditNewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditNewsBinding
    var title = ""
    var image = ""
    var detail = ""
    var id = ""
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
    var selectedImageUri : Uri? = null
    var REQUEST_CODE_PICK_IMAGE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        supportActionBar!!.elevation = 0.0F
        val view = supportActionBar!!.customView
        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        Select_Page = intent.getIntExtra("Select_Page",0)
        imageButton.setOnClickListener {
            val intent = intent.putExtra("Select_Page",Select_Page)
            setResult( Activity.RESULT_OK,intent)
            finish()
        }





        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        var data = intent.extras
        id = intent.getStringExtra("id").toString()
        title = data?.getString("title").toString()
        image = data?.getString("image").toString()
        detail = data?.getString("detail").toString()



        binding.edtNewsname.setText(title)
        binding.imageSelected.setImageURI(null)
//        Glide.with(applicationContext).load(URL_API+image).into(binding.imageSelected)
        binding.newsText.setText(detail)

        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }
        binding.btnSubmitnews.setOnClickListener{

            if(selectedImageUri != null && binding.edtNewsname.text.toString().isNotEmpty() && binding.newsText.text.toString().isNotEmpty()){

                val api: NewsAPI = Retrofit.Builder()
                    .baseUrl(URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsAPI::class.java)

                val parcelFileDescriptor =  contentResolver?.openFileDescriptor(selectedImageUri!!, "r", null)

                val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
                var namefile= ""
                val returnCursor =contentResolver?.query(selectedImageUri!!, null, null, null, null)
                if (returnCursor != null) {
                    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    namefile = returnCursor.getString(nameIndex)
                    returnCursor.close()
                }
                val file = File(cacheDir,namefile)

                val outputStream = FileOutputStream(file)
                inputStream.copyTo(outputStream)

                val body = UploadRequestBody(file, "image")

                var name = binding.edtNewsname.text.toString()
                var detail = binding.newsText.text.toString()
                AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)

                api.updateNews (
                    RequestBody.create(MediaType.parse("multipart/form-data"),name),
                    MultipartBody.Part.createFormData("image", file.name, body),
                    RequestBody.create(MediaType.parse("multipart/form-data"),detail),
                    RequestBody.create(MediaType.parse("multipart/form-data"), id)
                ).enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: retrofit2.Response<News>) {
                        if (response.isSuccessful) {
                            Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(applicationContext,"Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<News>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                    }
                })

            }else{
                Toast.makeText(applicationContext,"กรุณากรอกข้อมูลให้สมบูรณ์", Toast.LENGTH_LONG).show()
            }

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


}