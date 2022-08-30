package com.pac.kotlin_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pac.kotlin_mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}