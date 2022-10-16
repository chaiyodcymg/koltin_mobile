package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityEditPostBinding
import com.pac.kotlin_mobile.databinding.ActivityEditPostLostCatBinding
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
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

class EditPostLostCatActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditPostLostCatBinding
    var selectedImageUri : Uri? = null
    var REQUEST_CODE_PICK_IMAGE = 100
    lateinit var AUTH : SharedPreferences
    var URL_API = URL.URL_API
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var Select_Page : Int = R.id.page_1
        val actionBar : ActionBar? = supportActionBar
        binding = ActivityEditPostLostCatBinding.inflate(layoutInflater)
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
        val type = intent.getStringExtra("type")?.toInt()

        Log.i("Types","${type}")
       if(type == 1){
            binding.catlost.isChecked = true
        }else if(type == 2){
           binding.foundcat.isChecked = true
        }

        val id = intent.getStringExtra("id").toString()
        val namecat = intent.getStringExtra("namecat")
        val gender = intent.getStringExtra("gender")
        val color = intent.getStringExtra("color")
        val vaccine = intent.getStringExtra("vaccine")
        val date= intent.getStringExtra("date")
        val species = intent.getStringExtra("species")
        val more_info = intent.getStringExtra("more_info")
        val image = intent.getStringExtra("image")
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
        val dateplace = intent.getStringExtra("dateplace")
        val notice_point = intent.getStringExtra("notice_point")
        val place_to_found = intent.getStringExtra("place_to_found")
        Glide.with(applicationContext).load(URL_API+image).into(binding.imageSelected)

        binding.edtName.setText(namecat)
        if(gender == "ผู้") {
            binding.male.isChecked = true
        }else{
            binding.female.isChecked = true
        }
        binding.edtColor.setText(color)
        binding.edtVacine.setText(vaccine)
        binding.date.text = date
        binding.edtSpecies.setText(species)
        binding.edtMore.setText(more_info)
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
        binding.dateplace.text = dateplace
        binding.edtSpot.setText(notice_point )
        binding.edtLostplace.setText(place_to_found)
        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }

        binding.submit.setOnClickListener{



            var status = "0"


            var selectID: Int = binding.gender.checkedRadioButtonId
            var radioButtonChecked: RadioButton = findViewById(selectID)
            var selectID2: Int = binding.radiogroup.checkedRadioButtonId
            var radioButtonChecked2: RadioButton = findViewById(selectID2)
            var type = "0"
            if(radioButtonChecked2.text.toString() == "แจ้งน้องแมวหาย"){
                type = "1"
            }else{
                type = "2"
            }
            if (binding.edtName.text.toString().isEmpty() || binding.gender.getCheckedRadioButtonId() == -1
                || binding.edtColor.text.toString().isEmpty() || selectedImageUri == null
                || binding.edtVacine.text.toString().isEmpty()|| binding.date.text.toString().isEmpty()
                || binding.edtSpecies.text.toString().isEmpty()|| binding.edtMore.text.toString().isEmpty()
                || binding.edtPlace.text.toString().isEmpty()|| binding.edtStreet.text.toString().isEmpty()
                || binding.edtTown.text.toString().isEmpty() || binding.edtDistrict.text.toString().isEmpty()
                || binding.edtProvince.text.toString().isEmpty() || binding.edtPostcode.text.toString().isEmpty()
                || binding.edtNameperson.text.toString().isEmpty()|| binding.edtLastnameperson.text.toString().isEmpty()
                || binding.edtPhone.text.toString().isEmpty()|| binding.edtEmail.text.toString().isEmpty()
                || binding.edtLineid.text.toString().isEmpty()|| binding.edtFacebook.text.toString().isEmpty()
            ){
                Toast.makeText(applicationContext,"กรุณาใส่ข้อมูลให้ครบทุกช่อง",
                    Toast.LENGTH_SHORT).show()
            }

            else{
                val parcelFileDescriptor = contentResolver?.openFileDescriptor(selectedImageUri!!, "r", null)

                val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
                var name = ""
                val returnCursor = contentResolver?.query(selectedImageUri!!, null, null, null, null)
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
                var selectID: Int? = binding.gender.checkedRadioButtonId
                var radioButtonChecked: RadioButton = findViewById(selectID!!)
                val api: LostcatAPI = Retrofit.Builder()
                    .baseUrl(URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(LostcatAPI::class.java)

                api.updateLostCat(
                    MultipartBody.Part.createFormData("image", file.name, body),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtName.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),radioButtonChecked.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtColor.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtVacine.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.date.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtSpecies.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtMore.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtPlace.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtStreet.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtTown.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtDistrict.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtProvince.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),  binding.edtPostcode.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),  binding.dateplace.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtSpot.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtLostplace.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtNameperson.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtLastnameperson.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),binding.edtPhone.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtEmail.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtLineid.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtFacebook.text.toString()),
                    RequestBody.create(MediaType.parse("multipart/form-data"),type),
                    RequestBody.create(MediaType.parse("multipart/form-data"),status),
                    RequestBody.create(MediaType.parse("multipart/form-data"), id)

                ).enqueue(object : Callback<UploadResponse> {
                    override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(applicationContext, "Seccessfully Edited",
                                Toast.LENGTH_LONG).show()
                         finish()
                        } else {
                            Toast.makeText(applicationContext, "Edited Failure",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message,
                            Toast.LENGTH_LONG).show()
                    }
                })
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