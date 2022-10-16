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
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.pac.kotlin_mobile.databinding.ActivityEditPostBinding
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

class EditPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditPostBinding
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    var image = ""
    var id = ""
    var selectedImageUri : Uri? = null
    var REQUEST_CODE_PICK_IMAGE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var Select_Page : Int = R.id.page_1
        val actionBar : ActionBar? = supportActionBar
        binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.selectVaccineDate.setOnClickListener {

            val newDateFragment = DatePickerFragment()
            val supportFragmentManager = supportFragmentManager
            if (supportFragmentManager != null) {
                newDateFragment.show(supportFragmentManager, "Date Picker")
            }
        }
        var data = intent.extras

        id = intent.getStringExtra("id").toString()
        val namecat = intent.getStringExtra("namecat")
        val gender = intent.getStringExtra("gender")
        val color = intent.getStringExtra("color")
        val vaccine = intent.getStringExtra("vaccine")
        val date = intent.getStringExtra("date")
        val species = intent.getStringExtra("species")
        val more_info = intent.getStringExtra("more_info")
        image = data?.getString("image").toString()
        val house_no = intent.getStringExtra("house_no")
        val street = intent.getStringExtra("street")
        val sub_district = intent.getStringExtra("sub_district")
        val district = intent.getStringExtra("district")
        val province = intent.getStringExtra("province")
        val post_address = intent.getStringExtra("post_address")
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val line_id = intent.getStringExtra("line_id")
        val facebook = intent.getStringExtra("facebook")


        binding.edtName.setText(namecat)
        if(gender == "ผู้") {
            binding.male.setChecked(true)
        }else{
            binding.famale.setChecked(true)
        }
        binding.edtColor.setText(color)
        binding.edtVacine.setText(vaccine)
        binding.date.setText(date)
        binding.edtSpecies.setText(species)
        binding.edtMore.setText(more_info)
        binding.imageSelected.setImageURI(null)
        binding.edtPlace.setText(house_no)
        binding.edtStreet.setText(street)
        binding.edtTown.setText(sub_district)
        binding.edtDistrict.setText(district)
        binding.edtProvince.setText(province)
        binding.edtPostcode.setText(post_address)
        binding.edtNameperson.setText(firstname)
        binding.edtLastnameperson.setText(lastname)
        binding.edtPhone.setText(phone)
        binding.edtEmail.setText(email)
        binding.edtLineid.setText(line_id)
        binding.edtFacebook.setText(facebook)
        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }

        binding.submitUpdate.setOnClickListener(){
            if(selectedImageUri != null){
                val api: Cat_API = Retrofit.Builder()
                    .baseUrl(URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Cat_API::class.java)

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

                AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
                var selectID: Int = binding.gender.checkedRadioButtonId
                var radioButtonChecked: RadioButton = findViewById(selectID)
                var name = binding.edtName.text.toString()
                var gender = radioButtonChecked.text.toString()
                var color = binding.edtColor.text.toString()
                var vaccine = binding.edtVacine.text.toString()
                var date_vaccine = binding.date.text.toString()
                var species = binding.edtSpecies.text.toString()
                var more = binding.edtMore.text.toString()
                var place = binding.edtPlace.text.toString()
                var street = binding.edtStreet.text.toString()
                var town = binding.edtTown.text.toString()
                var district = binding.edtDistrict.text.toString()
                var province = binding.edtProvince.text.toString()
                var postcode = binding.edtPostcode.text.toString()
                var namefp = binding.edtNameperson.text.toString()
                var namelp = binding.edtLastnameperson.text.toString()
                var phone = binding.edtPhone.text.toString()
                var email = binding.edtEmail.text.toString()
                var lineid = binding.edtLineid.text.toString()
                var facebook = binding.edtFacebook.text.toString()
                api.updatePost(
                    RequestBody.create(MediaType.parse("multipart/form-data"), id),
                    RequestBody.create(MediaType.parse("multipart/form-data"),name),
                    RequestBody.create(MediaType.parse("multipart/form-data"),gender),
                    RequestBody.create(MediaType.parse("multipart/form-data"),color),
                    RequestBody.create(MediaType.parse("multipart/form-data"),vaccine),
                    RequestBody.create(MediaType.parse("multipart/form-data"),date_vaccine),
                    RequestBody.create(MediaType.parse("multipart/form-data"),species),
                    RequestBody.create(MediaType.parse("multipart/form-data"),more),
                    MultipartBody.Part.createFormData("image", file.name, body),
                    RequestBody.create(MediaType.parse("multipart/form-data"),place),
                    RequestBody.create(MediaType.parse("multipart/form-data"),street),
                    RequestBody.create(MediaType.parse("multipart/form-data"),town),
                    RequestBody.create(MediaType.parse("multipart/form-data"),district),
                    RequestBody.create(MediaType.parse("multipart/form-data"),province),
                    RequestBody.create(MediaType.parse("multipart/form-data"),postcode),
                    RequestBody.create(MediaType.parse("multipart/form-data"),namefp),
                    RequestBody.create(MediaType.parse("multipart/form-data"),namelp),
                    RequestBody.create(MediaType.parse("multipart/form-data"),phone),
                    RequestBody.create(MediaType.parse("multipart/form-data"),email),
                    RequestBody.create(MediaType.parse("multipart/form-data"),lineid),
                    RequestBody.create(MediaType.parse("multipart/form-data"),facebook)
                )
                    .enqueue(object : Callback<Cat> {
                        override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                            if(response.isSuccessful) {
                                Toast.makeText(applicationContext, "Successfully Update", Toast.LENGTH_LONG).show()
                                finish()
                            }else{
                                Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<Cat>, t: Throwable) {
                            Toast.makeText(applicationContext.applicationContext, "Update Fail", Toast.LENGTH_LONG).show()
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

}