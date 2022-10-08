package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class catfindhome_more : Fragment() {
    private lateinit var binding: FragmentCatfindhomeMoreBinding
    private lateinit var bindinglayout: CatfindhomeMoreLayoutBinding
    lateinit var AUTH : SharedPreferences
    var PostList = arrayListOf<Findhouse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatfindhomeMoreBinding.inflate(layoutInflater)
        bindinglayout = CatfindhomeMoreLayoutBinding.inflate(layoutInflater)

        // แก้ไขปุ่มย้อนกลับ
        (activity as AppCompatActivity).supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0.0F
        val view = (activity as AppCompatActivity).supportActionBar?.customView

        val imageButton = view?.findViewById<View>(R.id.action_bar_back)
        imageButton?.setOnClickListener {
           requireActivity().supportFragmentManager.beginTransaction().add(
                R.id.frameLayout,
                HomeFragment()
            ).commit()
            (activity as AppCompatActivity).supportActionBar?.setCustomView(null)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
            binding.recyclerView.getContext(), DividerItemDecoration.HORIZONTAL
        )
        )
        callData()
//        binding.catFind.setOnClickListener {
//            val intent = Intent(requireActivity().applicationContext, DetailFindhouseActivity::class.java)
//            startActivity(intent)
//        }
//        binding.btnHome.setOnClickListener {
//            var detailfragment: Fragment =  FragmentDetail()
//
//            replaceFragment(detailfragment)
//        }
        return binding.root
    }
    fun callData(){
        val api: FindhouseAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindhouseAPI::class.java)
        api.retrievePost()
            .enqueue(object: Callback<List<Findhouse>> {
                override fun onResponse(call: Call<List<Findhouse>>, response:
                Response<List<Findhouse>>
                ) {
                    PostList.clear();
                    if(response.isSuccessful){
                        response.body()?.forEach {
                            if(response.isSuccessful) {
                                PostList.add(Findhouse(
                                    it.id,
                                    it.name,
                                    it.gender,
                                    it.color,
                                    it.vaccine,
                                    it.date_vaccine,
                                    it.species,
                                    it.more_info,
                                    it.image,
                                    it.house_no,
                                    it.street,
                                    it.sub_district,
                                    it.district,
                                    it.province,
                                    it.post_address,
                                    it.firstname,
                                    it.lastname,
                                    it.phone,
                                    it.email,
                                    it.line_id,
                                    it.facebook,
                                    it.type,
                                    it.status,
                                    it.user_id
                                    ))
                            }

                        }
                        binding.recyclerView.adapter = FindHomeMoreAdapter(PostList,requireContext(), requireActivity() as MainActivity,layoutInflater)
                        binding.recyclerView.adapter?.notifyDataSetChanged()
                    }
                    else{
                        Toast.makeText(requireActivity().applicationContext,"error", Toast.LENGTH_SHORT).show()

                    }

                }
                override fun onFailure(call: Call<List<Findhouse>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
    fun replaceFragment(someFragment: Fragment) {
        var binding: ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}