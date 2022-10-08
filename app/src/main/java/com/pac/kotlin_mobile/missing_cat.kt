package com.pac.kotlin_mobile

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.FragmentMissingCatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class missing_cat : Fragment() {
    private lateinit var binding: FragmentMissingCatBinding
    var LostList = arrayListOf<Lostcat>()
    lateinit var AUTH : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMissingCatBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        callLostcat()
        return binding.root
    }

    fun callLostcat(){
        LostList.clear();
        val api: LostcatAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LostcatAPI::class.java)
        api.getCatlostmore()
            .enqueue(object: Callback<List<Lostcat>> {
                override fun onResponse(call: Call<List<Lostcat>>, response: Response<List<Lostcat>>) {
                    if(response.isSuccessful){
                        response.body()?.forEach {
                            LostList.add(Lostcat(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,it.post_address,it.date,it.notice_point,it.place_to_found,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook,it.type,it.status,it.user_id))
                        }
                        //// Set Data to RecyclerRecyclerView

                        binding.recyclerView.adapter = CatLostMoreAdapter(LostList,requireActivity().applicationContext,requireActivity() as MainActivity,layoutInflater)

                    }

                }
                override fun onFailure(call: Call<List<Lostcat>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}