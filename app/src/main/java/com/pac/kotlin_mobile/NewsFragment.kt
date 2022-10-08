package com.pac.kotlin_mobile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.URL.URL_API
import com.pac.kotlin_mobile.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    lateinit var AUTH : SharedPreferences
    var NewsList = arrayListOf<News>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(
            binding.recyclerView.getContext(),DividerItemDecoration.HORIZONTAL
        ))
        callNewsData()
        binding.fab.setOnClickListener() {
            val intent = Intent(requireActivity().applicationContext, AddNewsActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun callNewsData(){
        NewsList.clear();
        val api: NewsAPI = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
        api.retrieveNews()
            .enqueue(object: Callback<List<News>>{
                override fun onResponse(call: Call<List<News>>, response:
                Response<List<News>>
                ) {
                    response.body()?.forEach {
                        NewsList.add(News(it.id,it.title,it.image,it.detail,it.user_id))
                    }
//// Set Data to RecyclerRecyclerView

                    binding. recyclerView.adapter = NewsAdapter(NewsList,requireActivity().applicationContext, requireActivity() as MainActivity,layoutInflater)

                }
                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Toast.makeText(requireActivity().applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

}