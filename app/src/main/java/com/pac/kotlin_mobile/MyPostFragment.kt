package com.pac.kotlin_mobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.pac.kotlin_mobile.databinding.ActivityMainBinding

import com.pac.kotlin_mobile.databinding.FragmentMyPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyPostFragment : Fragment() {
    private lateinit var binding: FragmentMyPostBinding
    val postlist = arrayListOf<Postlist>()
    var URL_API = URL.URL_API


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyPostBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = MyPostAdapter(this.postlist,requireActivity().applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        binding.checkPostText.setOnClickListener {
            var fragment: Fragment? = null
            fragment = CheckPostFragment()
            replaceFragment(fragment)
        }
            callpostData()


//        bindingRV.deletePost.setOnClickListener {
//           val myBuilder = AlertDialog.Builder(requireActivity())
//            myBuilder.apply {
//                setMessage("ลบ")
//                setNegativeButton("Yes") {dialog, which ->
//                    api.deletePost(id)
//                        .enqueue(object : Callback<Cat> {
//                            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
//                                if(response.isSuccessful) {
//                                    Toast.makeText(requireActivity().applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
//                                }
//                            }
//
//                            override fun onFailure(call: Call<Cat>, t: Throwable) {
//                                Toast.makeText(requireActivity().applicationContext, "Faill Deleted", Toast.LENGTH_LONG).show()
//                            }
//                        })
//                }
//                setPositiveButton("No") {dialog, which -> dialog.cancel()}
//                show()
//            }
//        }

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
        api.getMypost()
            .enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response:
                Response<List<Cat>>
                ) {
                    response.body()?.forEach {
                        postlist.add(Postlist(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,
                            it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,
                            it.post_address,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook
                        )) }
//// Set Data to RecyclerRecyclerView
                    binding.recyclerView.adapter = MyPostAdapter(postlist,requireContext())
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }



                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }

            })
    }


    }


