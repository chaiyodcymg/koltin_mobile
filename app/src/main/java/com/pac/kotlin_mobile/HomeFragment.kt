package com.pac.kotlin_mobile

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.pac.kotlin_mobile.databinding.FragmentHomeBinding
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var AUTH : SharedPreferences
    var NewsList = arrayListOf<News>()
    var FindList = arrayListOf<Findhouse>()
    var LostList = arrayListOf<Lostcat>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.seemoreCat.setOnClickListener() {
            var seemorefragment: Fragment? = null
            seemorefragment = catfindhome_more()
            replaceFragment(seemorefragment)
        }
        binding.missingCat.setOnClickListener() {
            var missingCatfragment: Fragment? = null
            missingCatfragment = missing_cat()
            replaceFragment(missingCatfragment)
        }
        binding.newsView.layoutManager = LinearLayoutManager( requireActivity().applicationContext)
        binding.findhouseView.layoutManager = LinearLayoutManager( requireActivity().applicationContext)
        binding.lostcatView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        callNewsHome()
        callFindHome()
        callLostcat()
        return binding.root
    }


    fun callNewsHome(){
        NewsList.clear();
        val api: NewsAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
        api.gethomeNews()
            .enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if(response.isSuccessful){
                        NewsList.add( response.body()!!)
                        //// Set Data to RecyclerRecyclerView
                        Log.i("Event","${NewsList}")
                        binding.newsView.adapter = HomeAdapter(NewsList,requireActivity().applicationContext,requireActivity() as MainActivity,layoutInflater)

                    }
                }
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
    fun callFindHome(){
        FindList.clear();
        val api: FindhouseAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindhouseAPI::class.java)
        api.gethomeFind()
            .enqueue(object: Callback<List<Findhouse>> {
                override fun onResponse(call: Call<List<Findhouse>>, response: Response<List<Findhouse>>) {
                    if(response.isSuccessful){
                        response.body()?.forEach {
                            FindList.add(Findhouse(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,it.post_address,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook,it.type,it.status,it.user_id))
                        }
                        //// Set Data to RecyclerRecyclerView
                        Log.i("Event","${FindList}")
                        binding.findhouseView.adapter = HomeFindhouseAdapter(FindList,requireActivity().applicationContext,requireActivity() as MainActivity,layoutInflater)
                    }
                }
                override fun onFailure(call: Call<List<Findhouse>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun callLostcat(){
        LostList.clear();
        val api: LostcatAPI = Retrofit.Builder()
            .baseUrl(URL.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LostcatAPI::class.java)
        api.gethomeLost()
            .enqueue(object: Callback<List<Lostcat>> {
                override fun onResponse(call: Call<List<Lostcat>>, response: Response<List<Lostcat>>) {
                    if(response.isSuccessful){
                        response.body()?.forEach {
                            LostList.add(Lostcat(it.id,it.name,it.gender,it.color,it.vaccine,it.date_vaccine,it.species,it.more_info,it.image,it.house_no,it.street,it.sub_district,it.district,it.province,it.post_address,it.date,it.notice_point,it.place_to_found,it.firstname,it.lastname,it.phone,it.email,it.line_id,it.facebook,it.type,it.status,it.user_id))
                        }
                        //// Set Data to RecyclerRecyclerView

                        binding.lostcatView.adapter = HomeCatlostAdapter(LostList,requireActivity().applicationContext,requireActivity() as MainActivity,layoutInflater)

                    }

                }
                override fun onFailure(call: Call<List<Lostcat>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }


    fun replaceFragment(someFragment:Fragment){
        var binding: FragmentHomeBinding
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.frameLayout.id, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

