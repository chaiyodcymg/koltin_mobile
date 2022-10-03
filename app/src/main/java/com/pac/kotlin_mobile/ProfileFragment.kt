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

        binding.btnChangeToEdit.setOnClickListener {
            val  intent = Intent(requireActivity().applicationContext, EditProfileActivity::class.java)
            startActivity(intent)
        }
        return binding.root
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
                        binding.imageSelected.setImageURI(null)
                        Glide.with(requireActivity().applicationContext).load(URL_API +response.body()?.image_profile.toString()).into(binding.imageSelected)
                        binding.email.setText( "อีเมล : ${response.body()?.email.toString()}")
                        binding.firstname.setText("ชื่อ : ${response.body()?.firstname.toString()}")
                        binding.lastname.setText( "นามสกุล : ${response.body()?.lastname.toString()}")




                    }

                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {

                }


            })
    }





}


