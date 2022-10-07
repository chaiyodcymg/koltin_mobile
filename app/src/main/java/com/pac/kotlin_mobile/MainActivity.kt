package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    lateinit var AUTH : SharedPreferences


    var Select_Page : Int = R.id.page_1

    var URL_API = URL.URL_API
    var image_profile  = "@drawable/user"
    private var menu: Menu? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        var id =  AUTH.getString("id","")
        if(id?.isNotEmpty() == true){
            getData()
        }


        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.elevation = 0.0F
        supportActionBar!!.setCustomView(null)
        supportFragmentManager.beginTransaction().add(
            R.id.frameLayout,
            HomeFragment()
        ).commit()


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1-> {


                    Select_Page = R.id.page_1


                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        HomeFragment()
                    ).commit()
                    supportActionBar!!.setCustomView(null)
                    true
                }
                R.id.page_2 -> {

                    Select_Page = R.id.page_2
                    AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
                    var id =  AUTH.getString("id","")
                    if(id != null && id.isNotEmpty()){
                        supportFragmentManager.beginTransaction().replace(
                            R.id.frameLayout,
                            AddPostFragment()
                        ).commit()
                    }else{
                        supportFragmentManager.beginTransaction().replace(
                            R.id.frameLayout,
                            NotLoggedInFragment()
                        ).commit()

                    }
                    supportActionBar!!.setCustomView(null)
                    true
                }
                R.id.page_3 -> {

                    Select_Page = R.id.page_3
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        NewsFragment()
                    ).commit()
                    supportActionBar!!.setCustomView(null)
                    true
                }
                R.id.page_4 -> {

                    Select_Page = R.id.page_4
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        MyPostFragment()
                    ).commit()
                    supportActionBar!!.setCustomView(null)
                    true
                }
                else -> false
            }
        }




    }

    fun getData(){
        var api : UserAPI =   Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
        api.Profile(
            AUTH.getString("id","")!!

        ).enqueue(object : Callback<Profile> {

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful()){

                    image_profile =  URL_API +response.body()?.image_profile.toString()
                    Log.i("Event","${image_profile }")
                    setMenu(image_profile )
                }

            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.i("Events","${t.message}")
            }


        })
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        Log.i("Event","onPrepareOptionsMenu")
        this.menu = menu
        val settingsItem =  this.menu?.findItem(R.id.menu2)

        var api : UserAPI =   Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
        api.Profile(
            AUTH.getString("id","")!!

        ).enqueue(object : Callback<Profile> {

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful()){
                    Glide.with(this@MainActivity).asBitmap()
                        .load(URL_API +response.body()?.image_profile.toString())
                        .circleCrop()
                        .into(object : SimpleTarget<Bitmap?>(100, 100) {
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {

                                settingsItem?.icon = BitmapDrawable(resources, resource)

                            }

                        })
                }

            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.i("Events","${t.message}")
            }


        })
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.i("Event","onCreateOptionsMenu")

        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {

                val  intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra("Select_Page",Select_Page)
                startActivityForResult(intent ,1)
                supportActionBar!!.setCustomView(null)
            }
            R.id.menu2 -> {

                val  intent = Intent(applicationContext, ProfileActivity::class.java)
                intent.putExtra("Select_Page",Select_Page)

                startActivityForResult(intent ,1)
                supportActionBar!!.setCustomView(null)
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == RESULT_OK){
            Log.i("Event","${data!!.getIntExtra("Select_Page",0)}")
            Select_Page =  data!!.getIntExtra("Select_Page",0)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("Event","onResume")
        supportActionBar!!.setCustomView(null)
        var id =  AUTH.getString("id","")
        if(id?.isNotEmpty() == true){
            getData()

        }else{
            Log.i("Event","ข้อมูลว่างง")

            val settingsItem =  this.menu?.findItem(R.id.menu2)

            settingsItem?.setIcon(ContextCompat.getDrawable(this, R.drawable.user))
        }
        binding.bottomNavigation.selectedItemId =  Select_Page





    }

    override fun onBackPressed() {
        finishAffinity()
    }



    fun setMenu(image_url :String){
        val settingsItem =  this.menu?.findItem(R.id.menu2)

        Glide.with(this@MainActivity).asBitmap()
            .load(image_url)
            .circleCrop()
            .into(object : SimpleTarget<Bitmap?>(100, 100) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                    settingsItem?.icon = BitmapDrawable(resources, resource)

                }

            })
    }


//
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
//                    binding.imageSelected.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
//
//
//    private fun uploadImage() {
//
//
//        val parcelFileDescriptor =
//            contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return
//
//        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
//        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
//        val outputStream = FileOutputStream(file)
//        inputStream.copyTo(outputStream)
//
//
//        val body = UploadRequestBody(file, "image")
//    var api : UserAPI =    Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:3000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(UserAPI::class.java)
//
//        api.uploadImage(
//            MultipartBody.Part.createFormData(
//                "image",
//                file.name,
//                body
//            ),
//            RequestBody.create(MediaType.parse("multipart/form-data"), "json")
//        ).enqueue(object : Callback<UploadResponse> {
//            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<UploadResponse>,
//                response: Response<UploadResponse>
//            ) {
//
//            }
//        })
//
//    }
//
//
//
//    companion object {
//        const val REQUEST_CODE_PICK_IMAGE = 101
//    }


//    fun logout(v: View){
//        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
//        AUTH.edit{clear()}
//        val i = Intent(applicationContext, LoginActivity::class.java)
//        startActivity(i)
//    }

}