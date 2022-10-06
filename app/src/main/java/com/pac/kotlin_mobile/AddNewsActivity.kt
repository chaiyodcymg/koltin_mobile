package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pac.kotlin_mobile.URL.URL_API
import com.pac.kotlin_mobile.databinding.ActivityAddNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewsBinding
    lateinit var AUTH : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            var id =  AUTH.getString("id","").toString()
            api.insertNews(
                name,
                imageSelected,
                detail,
                id
            ).enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: retrofit2.Response<News>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
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