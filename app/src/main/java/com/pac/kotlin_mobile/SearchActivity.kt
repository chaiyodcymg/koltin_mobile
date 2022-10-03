package com.pac.kotlin_mobile

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchBinding
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
    var data_search_List = arrayListOf<Cat_search>()
    var URL_API = URL.URL_API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.action_bar_search)
        val view = supportActionBar!!.customView

        val imageButton = view.findViewById<View>(R.id.action_bar_back)
        imageButton.setOnClickListener {
            val intent = intent.putExtra("Select_Page",Select_Page)
            setResult( Activity.RESULT_OK,intent)
            finish()
        }
        Select_Page = intent.getIntExtra("Select_Page",0)

        binding.recyclerView.adapter = SearchAdapter(this.data_search_List,applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL) )

        var binding_actionbar = findViewById(R.id.action_bar_searchView) as SearchView

        binding_actionbar .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String): Boolean {
                Log.i("Events",search)
                if(search.trim().isNotEmpty() && search.isNotEmpty()){


                    val api: Cat_API = Retrofit.Builder()
                        .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(Cat_API::class.java)
                    api.search(search)
                        .enqueue(object : Callback<List<Cat>> {
                            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                                if (response.isSuccessful()) {
                                    data_search_List.clear()
                                    Log.i("Events" ,"${response.body()}")
                                    response.body()?.forEach {
                                        Log.i("Events","${it.id}")
                                        data_search_List.add(Cat_search(it.id ,it.color , it.species, it.name))
                                    }
                                    binding.recyclerView.adapter?.notifyDataSetChanged()
                                    binding.recyclerView.adapter = SearchAdapter(data_search_List,applicationContext)
                                }
                            }

                            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
                            }
                        })
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

        })

    }




}
