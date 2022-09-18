package com.pac.kotlin_mobile


import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract.Attendees.query
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityProfileBinding
import com.pac.kotlin_mobile.databinding.FragmentProfileBinding
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



class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var AUTH : SharedPreferences
    var URL_API = URL.URL_API
var REQUEST_CODE_PICK_IMAGE = 101
    var selectedImageUri : Uri? = null
    var image_profile : String? = null
    var email_Get : String? = null
    var fname_Get : String? = null
    var lname_Get : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)
        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        getData()


        binding.btnLogout.setOnClickListener {


            AUTH.edit { clear() }
            var binding: ActivityProfileBinding
            binding = ActivityProfileBinding.inflate(layoutInflater)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(binding.frameLayout.id, LoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.imageSelected.setOnClickListener {
            openImageChooser()
        }
        binding.btnImageUpload.setOnClickListener {
            uploadImage()
        }
        return binding.root
    }
    fun getData(){
        var api : UserAPI =   Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
        api.Profile(AUTH.getString("id","")!!)
            .enqueue(object : Callback<Profile> {

                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful()){
                        binding.imageSelected.setImageURI(null)
                        Glide.with(requireActivity().applicationContext).load(URL_API +response.body()?.image_profile.toString()).into(binding.imageSelected)
                        binding.email.setText( response.body()?.email.toString())
                        binding.firstname.setText(response.body()?.firstname.toString())
                        binding.lastname.setText( response.body()?.lastname.toString())




                    }

                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {

                }


            })
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


                val parcelFileDescriptor =  requireActivity()?.contentResolver?.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

                val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                var name = ""
                val returnCursor = requireActivity()?.contentResolver?.query(selectedImageUri!!, null, null, null, null)
                if (returnCursor != null) {
                    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    name = returnCursor.getString(nameIndex)
                    returnCursor.close()
                }
                val file = File(requireActivity().cacheDir,  name)

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
                        if (response.isSuccessful()){
                            Toast.makeText(requireActivity().applicationContext,"แก้ไขสำเร็จ ", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<UploadResponse>, t: Throwable) {

                    }


                })
        }else{

            api.updateprofile_noImage(AUTH.getString("id","")!! , email , fname, lname)
                .enqueue(object : Callback<Update> {

                override fun onResponse(call: Call<Update> , response: Response<Update>) {

                }
                    override fun onFailure(call: Call<Update> , t: Throwable) {

                    }
                })
         }
    }
}


