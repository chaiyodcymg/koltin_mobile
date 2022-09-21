package com.pac.kotlin_mobile

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pac.kotlin_mobile.databinding.ActivitySearchBinding
import java.lang.reflect.Type

class SearchActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchBinding
    lateinit var AUTH : SharedPreferences
    var Select_Page : Int = R.id.page_1
    var data_search_List = arrayListOf<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // แก้ไขปุ่มย้อนกลับ
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
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


        SocketHandler.setSocket()
        SocketHandler.establishConnection()
        val mSocket = SocketHandler.getSocket()


       binding.search.addTextChangedListener(object :TextWatcher{

           override fun afterTextChanged(p0: Editable?) {
               binding.recyclerView.adapter = SearchAdapter(data_search_List, applicationContext)
           }
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

           override  fun  onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {

               if(value?.trim()?.length!! > 0 ){


               mSocket.emit("search",value)
               mSocket.on("result") { args ->
                   data_search_List.clear()
                   val listType: Type = object : TypeToken<ArrayList<Search?>?>() {}.type
                   val List: List<Search> = Gson().fromJson(args[0].toString(), listType)
                   List.forEach{
                       data_search_List.add(Search(it.email,it.firstname,it.id,it.image_profile, it.lastname , it.password , it.role))

                   }

//                   data_search_List.add(Search("aa", "mn", 11,"Aeever", "kuyrai", "kuyy", 0))
                   Log.i("Events","${ data_search_List}")

               }
                   binding.recyclerView.adapter = SearchAdapter(data_search_List, applicationContext)


               }else{
                   data_search_List.clear()
                   binding.recyclerView.adapter = SearchAdapter(data_search_List, applicationContext)
               }
           }
       })



    }
}