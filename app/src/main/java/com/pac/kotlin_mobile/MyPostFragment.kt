package com.pac.kotlin_mobile

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.ActivityMainBinding
import com.pac.kotlin_mobile.databinding.FragmentMyPostBinding
import com.pac.kotlin_mobile.databinding.MypostLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPostFragment : Fragment() {
    private lateinit var binding: FragmentMyPostBinding
    private lateinit var bindingRV: MypostLayoutBinding
    val postlist = arrayListOf<Postlist>()
    var URL_API = URL.URL_API

    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyPostBinding.inflate(layoutInflater)
        bindingRV = MypostLayoutBinding.inflate(layoutInflater)
//        binding.recyclerView.adapter = MyPostAdapter(this.postlist,requireActivity().applicationContext)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        var role =  AUTH.getString("role","")
        if(role.toString() == "1"){


            binding.checkPostText.setOnClickListener {
                var fragment: Fragment? = null
                fragment = CheckPostFragment()
                replaceFragment(fragment)
            }
        }else{
            Log.i("Event","0")

            binding.cardMypost.layoutParams.width = 995

            binding.myPostText.layoutParams.width = 995
            binding.cardCheckpost.visibility = View.GONE
        }




        callpostData()
        return binding.root
    }



    fun replaceFragment(someFragment: Fragment) {
        var binding: ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



    fun callpostData () {
        val api: Cat_API = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Cat_API::class.java)
        AUTH = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        var id =  AUTH.getString("id","")
        api.getMypost(id.toString().toInt())
            .enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response:
                Response<List<Cat>>
                ) {
                    response.body()?.forEach {

                        postlist.add(Postlist(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,it.post_address,it.date,it.notice_point,it.place_to_found,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook,it.type,it.post_id))
                    }


                    // Set Data to RecyclerRecyclerView

                    binding.recyclerView.adapter = MyPostAdapter(postlist,requireContext())
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }

            })
    }



}



