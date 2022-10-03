package com.pac.kotlin_mobile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.pac.kotlin_mobile.databinding.ActivityEditProfileBinding


class EditProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditProfileBinding
    var Select_Page : Int = R.id.page_1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
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
    }
//    private fun openImageChooser() {
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val mimeTypes = arrayOf("image/jpeg", "image/png")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE  )
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CODE_PICK_IMAGE -> {
//                    selectedImageUri = data?.data
//                    binding.imageSelected.setImageURI(null)
//                    binding.imageSelected.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
//
//
//    private fun uploadImage() {
//
//        val email = binding.email.text.toString()
//        val fname = binding.firstname.text.toString()
//        val lname = binding.lastname.text.toString()
//
//        var api : UserAPI =   Retrofit.Builder()
//            .baseUrl(URL_API)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(UserAPI::class.java)
//
//        if (selectedImageUri != null){
//
//
//            val parcelFileDescriptor =  requireActivity()?.contentResolver?.openFileDescriptor(selectedImageUri!!, "r", null) ?: return
//
//            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
//            var name = ""
//            val returnCursor = requireActivity()?.contentResolver?.query(selectedImageUri!!, null, null, null, null)
//            if (returnCursor != null) {
//                val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//                returnCursor.moveToFirst()
//                name = returnCursor.getString(nameIndex)
//                returnCursor.close()
//            }
//            val file = File(requireActivity().cacheDir,  name)
//
//            val outputStream = FileOutputStream(file)
//            inputStream.copyTo(outputStream)
//
//
//            val body = UploadRequestBody(file, "image")
//
//
//            api.updateprofile_withImage(
//                MultipartBody.Part.createFormData(
//                    "image",
//                    file.name,
//                    body
//                ),
//                RequestBody.create(MediaType.parse("multipart/form-data"),AUTH.getString("id","")) ,
//                RequestBody.create(MediaType.parse("multipart/form-data"), email ),
//                RequestBody.create(MediaType.parse("multipart/form-data"), fname),
//                RequestBody.create(MediaType.parse("multipart/form-data"), lname)
//
//            ).enqueue(object : Callback<UploadResponse> {
//
//                override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
//                    if (response.isSuccessful()){
//                        Toast.makeText(requireActivity().applicationContext,"แก้ไขสำเร็จ ", Toast.LENGTH_LONG).show()
//                    }
//
//                }
//
//                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//
//                }
//
//
//            })
//        }else{
//
//            api.updateprofile_noImage(AUTH.getString("id","")!! , email , fname, lname)
//                .enqueue(object : Callback<Update> {
//
//                    override fun onResponse(call: Call<Update>, response: Response<Update>) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(requireActivity().applicationContext,"แก้ไขสำเร็จ ", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                    override fun onFailure(call: Call<Update>, t: Throwable) {
//
//                    }
//                })
//        }
//    }
}