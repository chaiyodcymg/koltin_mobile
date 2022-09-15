package com.pac.kotlin_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.edit
import com.pac.kotlin_mobile.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    lateinit var AUTH : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)

//        AUTH.edit { clear() }

        var name =  AUTH.getString("id","")
        Log.i("Event", "${name}" )
        if(name != null && name.isNotEmpty()){
            val  intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }else{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }



    }

    override fun onResume() {
        super.onResume()
//        AUTH = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
//
//        var name =  AUTH.getString("id","")
//
//        if(name != null && name.isNotEmpty()){
//            val i = Intent(applicationContext, HomeActivity::class.java)
//
//            startActivity(i)
//        }else{
//            val i = Intent(applicationContext, LoginActivity::class.java)
//            startActivity(i)
//        }
        Log.i("Event", "Main ")


    }

}