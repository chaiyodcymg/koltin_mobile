package com.pac.kotlin_mobile

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityEditNewsBinding
import com.pac.kotlin_mobile.databinding.ActivityEditProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditNewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditNewsBinding
    var title = ""
    var image = ""
    var detail = ""
    var id = ""
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
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
        Glide.with(applicationContext).load(image).into(binding.imageSelected)
        binding.newsText.setText(detail)
        setContentView(binding.root )

        binding.btnSubmitnews.setOnClickListener{
            val api: NewsAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPI::class.java)
            var name = binding.edtNewsname.text.toString()
            var imageSelected = "gg"
            var detail = binding.newsText.text.toString()
            AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
            var user_id =  AUTH.getString("id","").toString()
            api.updateNews (
                id.toInt(),
                name,
                imageSelected,
                detail,
                user_id
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
        }
    }


}