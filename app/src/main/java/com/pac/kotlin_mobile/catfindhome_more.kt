package com.pac.kotlin_mobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentCatfindhomeMoreBinding
import com.pac.kotlin_mobile.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class catfindhome_more : Fragment() {
    private lateinit var binding: FragmentCatfindhomeMoreBinding
    var FindList = arrayListOf<Findhouse>()
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatfindhomeMoreBinding.inflate(layoutInflater)

        // แก้ไขปุ่มย้อนกลับ
        (activity as AppCompatActivity).supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0.0F
        val view = (activity as AppCompatActivity).supportActionBar?.customView

        val imageButton = view?.findViewById<View>(R.id.action_bar_back)


        imageButton?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                HomeFragment()
            ).commit()
            (activity as AppCompatActivity).supportActionBar?.setCustomView(null)


        }

        callFindHome()
        binding.morefindhouseView.layoutManager = LinearLayoutManager( requireActivity().applicationContext)

        return binding.root
    }

    fun callFindHome(){
        FindList.clear();
        val api: FindhouseAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindhouseAPI::class.java)
        api.getFindhouse()
            .enqueue(object: Callback<List<Findhouse>> {
                override fun onResponse(call: Call<List<Findhouse>>, response: Response<List<Findhouse>>) {
                    if(response.isSuccessful){
                        response.body()?.forEach {
                            FindList.add(Findhouse(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,it.post_address,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook,it.type,it.status,it.user_id))
                        }
                        //// Set Data to RecyclerRecyclerView
                        binding.morefindhouseView.adapter = CatFindhouseMoreAdapter(FindList,requireActivity().applicationContext, requireActivity() as MainActivity,layoutInflater)

                    }

                }
                override fun onFailure(call: Call<List<Findhouse>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}