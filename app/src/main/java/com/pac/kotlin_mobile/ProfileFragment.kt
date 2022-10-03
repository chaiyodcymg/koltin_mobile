package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.pac.kotlin_mobile.databinding.ActivityProfileBinding
import com.pac.kotlin_mobile.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    override fun onResume() {
        super.onResume()
        getData()
    }



}