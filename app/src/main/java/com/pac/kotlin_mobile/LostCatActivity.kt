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
import androidx.fragment.app.Fragment
import com.pac.kotlin_mobile.databinding.ActivityAddCatFindhouseBinding
import com.pac.kotlin_mobile.databinding.ActivityLostCatBinding
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

class LostCatActivity : AppCompatActivity() {
    lateinit var binding : ActivityLostCatBinding
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    var selectedImageUri : Uri? = null
    var REQUEST_CODE_PICK_IMAGE = 100
    var Select_Page : Int = R.id.page_1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLostCatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
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
        binding.selectDate.setOnClickListener {

            val newDateFragment = DatePickerPlace()
            val supportFragmentManager = supportFragmentManager
            if (supportFragmentManager != null) {
                newDateFragment.show(supportFragmentManager, "Date Picker")
            }
        }

        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }

        binding.submit.setOnClickListener {
            binding.submit.isEnabled = false
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
            var status  = "0"
            var id =  AUTH.getString("id","")
            val api: LostcatAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LostcatAPI::class.java)





            api.insertpost(
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
                RequestBody.create(MediaType.parse("multipart/form-data"), id.toString())
            ).enqueue(object : Callback<UploadResponse> {
                override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Seccessfully Inserted",
                            Toast.LENGTH_LONG).show()
                        binding.submit.isEnabled = true
                      finish()
                    } else {
                        Toast.makeText(applicationContext, " Insert Failure",
                            Toast.LENGTH_LONG)
                            .show()
                        binding.submit.isEnabled = true
                    }
                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,"Error onFailure " + t.message,
                        Toast.LENGTH_LONG).show()
                    binding.submit.isEnabled = true
                }
            })
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