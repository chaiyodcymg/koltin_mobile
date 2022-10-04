package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pac.kotlin_mobile.URL.URL_API
import com.pac.kotlin_mobile.databinding.FragmentAddNewsBinding
import com.pac.kotlin_mobile.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddNewsFragment : Fragment() {
    private lateinit var binding: FragmentAddNewsBinding
    var URL_API = URL.URL_API
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewsBinding.inflate(layoutInflater)
        binding.btnSubmitnews.setOnClickListener{
            val api: NewsAPI = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPI::class.java)
            var name = binding.edtNewsname.text.toString()
            var imageSelected = "gg"
            var detail = binding.newsText.text.toString()
            AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
            var id =  AUTH.getString("id","").toString()
            api.insertNews(
                name,
                imageSelected,
                detail,
                id
                ).enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: retrofit2.Response<News>) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireActivity().applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(requireActivity().applicationContext,"Eror", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
        return binding.root
    }

}